package model.facade;

import model.JsonSerializable;
import model.Model;
import model.base.GameImpl;

/**
 * Created by Jon George on 3/7/14.
 */
public class GameFacade{
    private Model m_model;

    public GameFacade(Model model) {
        m_model = model;
    }

    public boolean onAddAI(){
        //TODO implement this method to use model
        return true;
    }
    public String onGetCommands(){
        //TODO implement this method to use model
        return "[{\"number\":8,\"type\":\"rollNumber\",\"playerIndex\":0},{\"type\":\"finishTurn\",\"playerIndex\":0}]";
    }
    public String onListAI(){
        return "[\"LARGEST_ARMY\"]";
    }
    public boolean onPostCommands(){
        //TODO implement this method to use model
        return true;
    }
    public String onModelRequest(){
        GameImpl model = new GameImpl();
        return model.toJson();
    }
    public String onReset(){
        GameImpl model = new GameImpl();
        return model.toJson();
    }
}
