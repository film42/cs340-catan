package model.facade;

import com.google.gson.Gson;
import model.JsonSerializable;
import model.Model;
import model.preview.GameStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qzcx on 3/7/14.
 */
public class GamesFacade{
    private Model m_model;

    public GamesFacade(Model model) {
        m_model = model;
    }

    public String onCreateGame(){
        //TODO implement this method to use model
        return "{ \"title\": \"New Game NAme\", \"id\": 4, \"players\": [ {}, {}, {}, {} ] }";
    }
    public boolean onJoinGame(){
        //TODO implement this method to use model
        return true;
    }
    public String onListGames(){
        //TODO Create a transparent list class for serializing `no root json lists`
        List<GameStub> gS = new ArrayList<GameStub>();
        gS.add(new GameStub());
        gS.add(new GameStub());
        gS.add(new GameStub());

        Gson gson = new Gson();
        return gson.toJson(gS);
    }
}
