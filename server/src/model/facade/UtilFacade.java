package model.facade;

import model.IModel;
import route.game.ModelRoute;

/**
 * Created by qzcx on 3/6/14.
 * This class will contain the interface for the user/login, user/register, util/changeLogLevel
 *
 */
public class UtilFacade {

    private IModel m_model;

    public UtilFacade(IModel model) {
        m_model = model;
    }

    /**
     *
     * @return success/failure based on whether the password matches the username
     */
    public boolean onUserLogin(){
        //TODO implement this method to use model
        //1. check if user is valid
        //2. check if password matches
        return true;
    }

    /**
     *
     * @return success/failure based on whether the username/password is valid
     */
    public boolean onUserRegister(){
        //TODO implement this method
        //1. check if username already exists, return false
        //2. add username/password to model, return true
        return true;
    }

    public boolean onChangeLogLevel(){
        //TODO implement this method
        //1. change logging level
        return true;
    }
}
