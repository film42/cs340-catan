package persistance;

//import modelInterfaces.base.Game;
//import modelInterfaces.users.User;

import java.util.List;

/**
 * Created by Jon on 4/4/14.
 */
public class PersistenceManager {


    private GamesDAO gamesDAO;
    private UsersDAO usersDAO;
    private int saveInterval;

    /**
     * load in config data for plug in.
     * instantiate persistenceProvider
     * get gamesDao and usersDao
     */
    public PersistenceManager(int saveInterval){
        this.saveInterval = saveInterval;
        PersistenceProvider.initialize();
        gamesDAO = new GamesDAO();
        usersDAO = new UsersDAO();
    }

    /**
     *
     * @return a persistance provider given by the plugin or uses the default provider.
     */
    private PersistenceProvider loadPlugin(){
        return null;
    }

    /**
     * Saves the given user information to the server.
     * @param newUser - the user to save
     */
    public void addUser(UserDTO newUser){
        PersistenceProvider.beginTransaction();
        if (usersDAO.addUser(newUser)){
            PersistenceProvider.endTransaction(true);
        }
        else{
            PersistenceProvider.endTransaction(false);
        }
    }

    /**
     * saves the game given.
     * @param game
     */
    public void addGame(GameDTO game){

        PersistenceProvider.beginTransaction();
        if (gamesDAO.addGame(game.getName(), game.getInitialPoint()) > 0){
            PersistenceProvider.endTransaction(true);
        }
        else{
            PersistenceProvider.endTransaction(false);
        }
    }

    /**
     *
     * @return a list of users stored in the database.
     */
    public List<UserDTO> loadUsers(){

        PersistenceProvider.beginTransaction();
        List<UserDTO> users = usersDAO.getUsers();
        PersistenceProvider.endTransaction(true);
        return users;
    }

    /**
     *
     * @return a list of games stored in the database.
     */
    public List<GameDTO> loadGames(){

        PersistenceProvider.beginTransaction();
        List<GameDTO> games = gamesDAO.getGames();
        PersistenceProvider.endTransaction(true);
        return games;
    }

    /**
     *
     * @param game
     */
    public void updateCommandList(GameDTO game) {

        PersistenceProvider.beginTransaction();
        if (gamesDAO.updateCommandList(game.getId(), game.getCommandList())){
            PersistenceProvider.endTransaction(true);
        }
        else{
            PersistenceProvider.endTransaction(true);
        }

    }


}

