package persistance;

//import modelInterfaces.base.Game;
//import modelInterfaces.users.User;

import java.util.List;

/**
 * Created by Jon on 4/4/14.
 */
public class PersistenceManager {


    private SQLGamesDAO gamesDAO;
    private SQLUsersDAO usersDAO;
    private int saveInterval;

    /**
     * load in config data for plug in.
     * instantiate persistenceProvider
     * get gamesDao and usersDao
     */
    public PersistenceManager(int saveInterval){
        this.saveInterval = saveInterval;
        SQLPersistenceProvider.initialize();
        gamesDAO = new SQLGamesDAO();
        usersDAO = new SQLUsersDAO();
    }

    /**
     *
     * @return a persistance provider given by the plugin or uses the default provider.
     */
    private SQLPersistenceProvider loadPlugin(){
        return null;
    }

    /**
     * Saves the given user information to the server.
     * @param newUser - the user to save
     */
    public void addUser(UserDTO newUser){
        SQLPersistenceProvider.beginTransaction();
        if (usersDAO.addUser(newUser)){
            SQLPersistenceProvider.endTransaction(true);
        }
        else{
            SQLPersistenceProvider.endTransaction(false);
        }
    }

    /**
     * saves the game given.
     * @param game
     */
    public void addGame(GameDTO game){

        SQLPersistenceProvider.beginTransaction();
        if (gamesDAO.addGame(game.getName(), game.getInitialPoint()) > 0){
            SQLPersistenceProvider.endTransaction(true);
        }
        else{
            SQLPersistenceProvider.endTransaction(false);
        }
    }

    /**
     *
     * @return a list of users stored in the database.
     */
    public List<UserDTO> loadUsers(){

        SQLPersistenceProvider.beginTransaction();
        List<UserDTO> users = usersDAO.getUsers();
        SQLPersistenceProvider.endTransaction(true);
        return users;
    }

    /**
     *
     * @return a list of games stored in the database.
     */
    public List<GameDTO> loadGames(){

        SQLPersistenceProvider.beginTransaction();
        List<GameDTO> games = gamesDAO.getGames();
        SQLPersistenceProvider.endTransaction(true);
        return games;
    }

    /**
     *
     * @param game
     */
    public void updateCommandList(GameDTO game) {

        SQLPersistenceProvider.beginTransaction();
        if (gamesDAO.updateCommandList(game.getId(), game.getCommandList())){
            SQLPersistenceProvider.endTransaction(true);
        }
        else{
            SQLPersistenceProvider.endTransaction(true);
        }

    }


}

