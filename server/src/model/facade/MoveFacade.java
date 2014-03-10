package model.facade;

import model.JsonSerializable;
import model.base.Game;

/**
 * Created by qzcx on 3/7/14.
 */
public class MoveFacade {

    private JsonSerializable m_model;

    public MoveFacade(JsonSerializable model) {
        m_model = model;
    }

    public String onMove(){
        //TODO decide whether we want one method per move in this function
        //TODO implement this method to use model
        Game model = new Game();
        return model.toJson();
    }

}
