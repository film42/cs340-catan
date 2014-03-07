package model.facade;

import model.Game;
import model.IModel;

/**
 * Created by qzcx on 3/7/14.
 */
public class GameFacade{
    private IModel m_model;

    public GameFacade(IModel model) {
        m_model = model;
    }

    public boolean onAddAI(){
        //TODO implement this method to use model
        return true;
    }
    public String onListAI(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
    public String onGetCommands(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
    public boolean onPostCommands(){
        //TODO implement this method to use model
        return true;
    }
    public String onModelRequest(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
    public String onReset(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
}
