package model.preview;

import model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/9/14.
 */
public class GameStub extends Model {

    private String title;
    private int id;
    private List<PlayerStub> players;

    public GameStub() {
        this.title = "Testing!";
        this.id = -1;
        this.players = new ArrayList<PlayerStub>();

        this.players.add(new PlayerStub());
        this.players.add(new PlayerStub());
        this.players.add(new PlayerStub());
    }
}
