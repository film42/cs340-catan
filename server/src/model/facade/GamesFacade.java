package model.facade;

import model.JsonSerializable;

/**
 * Created by qzcx on 3/7/14.
 */
public class GamesFacade{
    private JsonSerializable m_model;

    public GamesFacade(JsonSerializable model) {
        m_model = model;
    }

    public String onCreateGame(){
        //TODO implement this method to use model
        return "Insert Sample JSON";
    }
    public boolean onJoinGame(){
        //TODO implement this method to use model
        return true;
    }
    public String onListGames(){
        //TODO implement this method to use model
        return "Insert sample JSON";
    }
}
