package persistance;

import modelInterfaces.base.Game;
import modelInterfaces.users.User;

import java.util.List;

/**
 * Created by Jon on 4/4/14.
 */
public class PersistenceManager {

    private PersistenceProvider persistenceProv;
    private GamesDAO gamesDAO;
    private UsersDAO usersDAO;
    private int saveInterval;

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
     * @return a persistance provider given by the plugin or uses the default provider.
     */
    private PersistenceProvider loadPlugin(){
        return null;
    }

    /**
     * Saves the given user information to the server.
     * @param newUser - the user to save
     */
    public void addUser(User newUser){

    }

    /**
     * saves the game given.
     * @param game
     */
    public void addGame(Game game){

    }

    /**
     *
     * @return a list of users stored in the database.
     */
    public List<User> loadUsers(){
        return null;
    }

    /**
     *
     * @return a list of games stored in the database.
     */
    public List<Game> loadGames(){
        return null;
    }

    /**
     *
     * @param game
     */
    public void updateCommandList(Game game){

    }

}
