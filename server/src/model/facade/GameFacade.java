package model.facade;

import model.JsonSerializable;
import model.base.Game;

/**
 * Created by qzcx on 3/7/14.
 */
public class GameFacade{
    private JsonSerializable m_model;

    public GameFacade(JsonSerializable model) {
        m_model = model;
    }

    public boolean onAddAI(){
        //TODO implement this method to use model
        return true;
    }
    public String onGetCommands(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
    public String onListAI(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
    public boolean onPostCommands(){
        //TODO implement this method to use model
        return true;
    }
    public String onModelRequest(){
        Game model = new Game();
        return model.toJson();
    }
    public String onReset(){
        //TODO implement this method to use model
        return "Insert Sample JSON here";
    }
}
