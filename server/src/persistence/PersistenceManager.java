package persistence;

import model.InjectorFactory;
import model.JsonImpl;
import model.base.CommandList;
import model.base.GameInfoImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.users.User;

import java.util.ArrayList;
import java.util.List;

import comm.moves.base.Command;

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
		// persistenceProv = PluginUtil.loadPlugin(pluginName)
        return null;
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
	 * @param game
	 */
	public void addGame(GameInfo gameInfo) {
		if (persistenceProv == null)
			return;

		persistenceProv.beginTransaction();
		GamesDAO gamesDAO = persistenceProv.getGamesDAO();
		gamesDAO.addGame(gameInfo.getTitle(), gameInfo.getData().toJson());
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
		ArrayList<GameInfo> gameList = new ArrayList<GameInfo>();

		for (int gameIndex = 0; gameIndex < gamesDAO.getGameIds().size(); gameIndex++) {

			// We need to assemble the command list first.
			CommandList commandList = new CommandList();
			for (int commandIndex = 0; commandIndex < JsonImpl.fromJson(gamesDAO.getCommandList(gameIndex), CommandList.class).size(); commandIndex++) {
				Command command = (Command) JsonImpl.fromJson(gamesDAO.getCommandList(gameIndex), CommandList.class).get(commandIndex);
				commandList.add(Command.commandForType("/moves/" + command.getType(), gamesDAO.getCommandList(gameIndex)));
			}

			// Construct a new GameInfo and pass in the Game object (from json) into the constructor
			GameInfo gameInfo = new GameInfoImpl(JsonImpl.fromJson(gamesDAO.getCheckpoint(gameIndex), Game.class));

			// Set a few more things for the GameInfo
			gameInfo.setCommandList(commandList);
			gameInfo.setId(gameIndex);
			gameInfo.setTitle(gamesDAO.getName(gameIndex));

			// Good to go, add it to the overall list
			gameList.add(gameInfo);
		}

		persistenceProv.commitTransaction();

		// Return the list of games
		return gameList;
    }

    /**
	 * Saves a copy of the current list of commands that have occured in the game up to that point. If the number of commands is divisible by our
	 * saveInterval, then it takes a snapshot of the game state and stores that in memory also.
	 * 
	 * @param game
	 */
	public void updateCommandList(GameInfo game) {

		persistenceProv.beginTransaction();
		GamesDAO gamesDAO = persistenceProv.getGamesDAO();

		// Get the json version of our command list
		String commandListJson = game.getCommandList().toJson();

		// send it to the database provider
		gamesDAO.updateCommandList(game.getId(), commandListJson);
    	
    	// if it's the saveInterval, then save the model snapshot too.
		if (commandNumber++ % saveInterval == 0) {
			gamesDAO.updateCheckpoint(game.getId(), game.getData().toJson(), commandNumber);
		}
    	
		persistenceProv.commitTransaction();
    	
    }

}
