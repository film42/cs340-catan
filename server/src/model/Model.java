package model;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import model.users.UserImpl;
import modelInterfaces.base.Game;
import modelInterfaces.users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qzcx on 3/7/14.
 */
public class Model extends JsonImpl {

    List<String> AITypes = new ArrayList<String>();

    Set<User> users;
    List<Game> games;
    Injector injector;

	@Inject
	public Model() {
        injector = Guice.createInjector(new ModelModule());
        users = new HashSet<User>();
        init();
	}

    public void init(){
        //initialize the server information
        User adam = injector.getInstance(User.class);
        adam.setName("Adam");
        adam.setPassword("adam");
        users.add(adam);
        User steve = injector.getInstance(User.class);
        steve.setName("Steve");
        steve.setPassword("steve");
        users.add(steve);
        User june = injector.getInstance(User.class);
        june.setName("June");
        june.setPassword("june");
        users.add(june);
        User garrett = injector.getInstance(User.class);
        garrett.setName("Garrett");
        garrett.setPassword("garrett");
        users.add(garrett);
    }
    /**
     *
     * @param username
     * @param password
     * @return true if user was added, false if user already exist.
     */
    public boolean addUser(String username, String password){
        User user = hasUserByName(username);
        if(user == null)
            return false;
        User newUser = injector.getInstance(User.class);
        newUser.setName(username);
        newUser.setPassword(password);
        users.add(newUser);
        return true;
    }

    /**
     * Checks for a certain user is registered, returns true if password matches.
     * @param username
     * @param password
     * @return true if user exists and password matchs, else false
     */
    public boolean hasUser(String username, String password){
        User user = hasUserByName(username);
        if(user == null)
            return false;
        if(user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Looks for a user by a given username
     * @param username
     * @return if the user doesn't exist then return null
     */
    private User hasUserByName(String username){
        for (User user : users) {
            if(user.getName().equals(username))
                return user;
        }
        return null;
    }

}
