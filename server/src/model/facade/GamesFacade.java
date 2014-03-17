package model.facade;

import com.google.gson.Gson;
import comm.request.CreateGameRequest;
import comm.request.JoinGameRequest;
import model.JsonSerializable;
import model.Model;
import model.preview.GameStub;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon George on 3/7/14.
 */
public class GamesFacade{
    private Model m_model;

    public GamesFacade(Model model) {
        m_model = model;
    }

    public String onCreateGame(CreateGameRequest createGameRequest ){
        m_model.createGame(createGameRequest);
        return "{ \"title\": \"New Game NAme\", \"id\": 4, \"players\": [ {}, {}, {}, {} ] }";
    }
    public boolean onJoinGame(JoinGameRequest joinGameRequest){
        //TODO implement this method to use model
        return true;
    }
    public String onListGames(){
        List<GameStub> gS = new ArrayList<>();
        List<GameInfo> games = m_model.getGames();
        for (GameInfo game : games) {
            gS.add(new GameStub(game.getId(), game.getTitle(), game.getData().getPlayers()));
        }
        Gson gson = new Gson();
        return gson.toJson(gS);
    }
}
