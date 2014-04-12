package persistence;

import comm.moves.base.InvalidCommandException;
import model.InjectorFactory;
import model.JsonImpl;
import model.base.CommandList;
import model.base.GameInfoImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comm.moves.base.Command;
import server.Server;

/**
 * Created by Jon on 4/4/14.
 */
public class PersistenceManager {

	private PersistenceProvider persistenceProv = null;
    private int saveInterval;
	private int commandNumber = 0;

    /**
     * load in config data for plug in.
     * instantiate persistenceProvider
     * get gamesDAO and usersDAO
     */
    public PersistenceManager(int saveInterval){
        this.saveInterval = saveInterval;
    }

    /**
     *
     * @return a persistence provider given by the plugin or uses the default provider.
     */
	public PersistenceProvider loadPlugin(String pluginName) {
        if(pluginName.equals(""))
			return null; // no plugin String
		PluginUtil pu = new PluginUtil();
		pu.loadPlugin(pluginName);
		persistenceProv = pu.getPersistenceProvider();
		return persistenceProv;
    }


    /**
	 * Saves the given user information to the database
	 * 
	 * @param newUser
	 *            - the user to save
	 */
    public void addUser(User newUser){
		if (this.persistenceProv == null)
			return;

		persistenceProv.beginTransaction();
		UsersDAO usersDAO = persistenceProv.getUsersDAO();
		usersDAO.addUser(new UserDTO(newUser.getId(), newUser.getName(), newUser.getPassword()));
		persistenceProv.commitTransaction();
    }

    /**
	 * Adds a new game to the persistent memory
	 * 
	 * @param gameInfo
	 */
	public void addGame(GameInfo gameInfo) {
		if (persistenceProv == null)
			return;

		persistenceProv.beginTransaction();
		GamesDAO gamesDAO = persistenceProv.getGamesDAO();
        //I'm concerned we don't pass the ID in.
		int gameId = gamesDAO.addGame(gameInfo.getTitle(), gameInfo.getData().toJson());
        gameInfo.setId(gameId);
		persistenceProv.commitTransaction();
    }

    /**
     *
     * @param gameInfo
     */
    public void joinGame(GameInfo gameInfo){
        if (persistenceProv == null)
            return;

        persistenceProv.beginTransaction();
        GamesDAO gamesDAO = persistenceProv.getGamesDAO();
        gamesDAO.joinGame(gameInfo.getId(), gameInfo.getData().toJson());
        persistenceProv.commitTransaction();
    }

    /**
	 * Loads the User Data Transfer Object, assembles new user objects with the data retrieved, and returns the list of new User objects.
	 * 
	 * @return a list of users stored in the database.
	 */
    public List<User> loadUsers(){
		if (this.persistenceProv == null)
			return null;

		persistenceProv.beginTransaction();
		UsersDAO usersDAO = persistenceProv.getUsersDAO();
		ArrayList<User> users = new ArrayList<User>();
		for (UserDTO uDTO : usersDAO.getUsers()) {
			User user = InjectorFactory.getInjector().getInstance(User.class);
			user.setId(uDTO.getId());
			user.setName(uDTO.getUsername());
			user.setPassword(uDTO.getPassword());
			users.add(user);
		}
		persistenceProv.commitTransaction();
		return users;
    }

    /**
	 * Loads all of the games from the persistent data, assembles new GameInfo objects with the data retrieved, and returns the list of GameInfos
	 * 
	 * @return a list of games stored in the database.
	 */
	public List<GameInfo> loadGames() {
		if (this.persistenceProv == null)
			return null;

		persistenceProv.beginTransaction();
		GamesDAO gamesDAO = persistenceProv.getGamesDAO();
		ArrayList<GameInfo> gameList = new ArrayList<>();

        List<Integer> gameIds = gamesDAO.getGameIds();
		for (int gameIndex = 0; gameIndex < gameIds.size(); gameIndex++) {
            loadGame(gamesDAO, gameList, gameIds.get(gameIndex));
        }

		persistenceProv.commitTransaction();

		// Return the list of games
		return gameList;
    }

    private void loadGame(GamesDAO gamesDAO, ArrayList<GameInfo> gameList, int gameId) {
        // We need to assemble the command list first.
        CommandList commandList = new CommandList();

        String commandListJson = gamesDAO.getCommandList(gameId);
        if(commandListJson != null ) {
            loadCommandList(commandList, commandListJson);
        }

        // Construct a new GameInfo and pass in the Game object (from json) into the constructor
        String checkpointJson = gamesDAO.getCheckpoint(gameId);
        GameInfo gameInfo;
        if(checkpointJson != null) {
            gameInfo = new GameInfoImpl(JsonImpl.fromJson(checkpointJson, Game.class));
        }else{ //if there is no checkpoint then load the initial.
            String initialJson = gamesDAO.getInitialModel(gameId);
            if(initialJson != null){
                gameInfo = new GameInfoImpl(JsonImpl.fromJson(initialJson, Game.class));
            }else{
                //this shouldn't happen
                Server.log.severe("Attempted to load Game, but no game data to load");
                return; //if there is no initial json either... return out.
            }
        }

        int cursor = gamesDAO.getCursor(gameId);
        //Catch up from the checkpoint to current state
        if(cursor >= 0){
            for(;cursor<commandList.size(); cursor++){
                try {
                    commandList.get(cursor).execute(gameInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidCommandException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set a few more things for the GameInfo
        gameInfo.setCommandList(commandList);
        gameInfo.setId(gameId);
        gameInfo.setTitle(gamesDAO.getName(gameId));

        // Good to go, add it to the overall list
        gameList.add(gameInfo);
    }

    private void loadCommandList(CommandList commandList, String commandListJson) {
        //undo the double serialization.
        List<String> jsonCommands = JsonImpl.fromJson(commandListJson, CommandList.class).getJsonCommands();
        for (int i = 0; i < jsonCommands.size(); i++) {
            String command = jsonCommands.get(i);
            Command genericCommand = JsonImpl.fromJson(command, Command.class);
            commandList.add(Command.commandForType("/moves/" + genericCommand.getType(), command));
        }
    }

    /**
	 * Saves a copy of the current list of commands that have occurred in the game up to that point. If the number of commands is divisible by our
	 * saveInterval, then it takes a snapshot of the game state and stores that in memory also.
	 * 
	 * @param game
	 */
	public void updateCommandList(GameInfo game) {
        if (this.persistenceProv == null)
            return ;
		persistenceProv.beginTransaction();
		GamesDAO gamesDAO = persistenceProv.getGamesDAO();

        CommandList commands = game.getCommandList();
		// Get the json version of our command list
		String commandListJson = commands.toJson();

		// send it to the database provider
		gamesDAO.updateCommandList(game.getId(), commandListJson);
    	
    	// if it's the saveInterval, then save the model snapshot too.
		if (commands.size() % saveInterval == 0) {
			gamesDAO.updateCheckpoint(game.getId(), game.getData().toJson(), commands.size());
		}
    	
		persistenceProv.commitTransaction();
    	
    }

    public void clearDatabase(){
        if (this.persistenceProv == null)
            return;
        persistenceProv.beginTransaction();
        persistenceProv.wipeDatabase();
        persistenceProv.commitTransaction();
    }

}
