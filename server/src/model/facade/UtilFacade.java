package model.facade;

import model.JsonSerializable;
import model.Model;
import comm.request.UserRequest;
import comm.request.ChangeLogLevelRequest;
import modelInterfaces.users.User;
import persistence.PersistenceManager;

/**
 * Created by Jon George on 3/6/14.
 * This class will contain the interface for the user/login, user/register, util/changeLogLevel
 *
 */
public class UtilFacade {

    private Model m_model;
    private PersistenceManager m_PersistenceManager;

    public UtilFacade(Model model, PersistenceManager myPersistenceManager) {
        m_model = model;
        m_PersistenceManager = myPersistenceManager;
    }

    /**
     *
     * @return success/failure based on whether the password matches the username
     */
    public boolean onUserLogin(UserRequest userRequest){
        return m_model.hasUser(userRequest.getName(), userRequest.getPassword());
    }

    /**
     *
     * @return success/failure based on whether the username/password is valid
     */
    public boolean onUserRegister(UserRequest userRequest){
        User newUser = m_model.addUser(userRequest.getName(), userRequest.getPassword());
        if(newUser == null) {
            return false;
        }
        m_PersistenceManager.addUser(newUser);
        return true;
    }

    public boolean onChangeLogLevel(ChangeLogLevelRequest changeLogLevelRequest){
        //This method is not required.
        //1. change logging level
        return true;
    }

    public int getUserId(String userName, String Password) {
        User user = m_model.findUserByName(userName);
        return user.getId();
    }
}
