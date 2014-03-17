package model.preview;

import model.JsonImpl;
import modelInterfaces.base.Player;
import modelInterfaces.users.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by: film42 on: 3/9/14.
 */
public class GameStub extends JsonImpl {

    private String title;
    private int id;
    private List<PlayerStub> players;

    public GameStub(int id, String title,  List<Player> players){
        this.id = id;
        this.title = title;
        this.players = new ArrayList<>();
        for (Player player : players) {
            this.players.add(new PlayerStub(player.getPlayerID(), player.getName(), player.getColor()));
        }
    }

    public GameStub() {
        this.title = "Testing!";
        this.id = -1;
        this.players = new ArrayList<PlayerStub>();

        this.players.add(new PlayerStub());
        this.players.add(new PlayerStub());
        this.players.add(new PlayerStub());
    }
}
