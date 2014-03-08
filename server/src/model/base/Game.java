package model.base;

import model.Model;
import model.map.Map;
import model.messaging.Chat;
import model.messaging.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/6/14.
 */
public class Game extends Model {
    private Deck deck;
    private Map map;
    private List<Player> players;
    private Log log;
    private Chat chat;
    private Resources bank;
    private TurnTracker turnTracker;
    private int biggestArmy;
    private int longestRoad;
    private int winner;
    private int revision;

    public Game() {
        deck = new Deck();

        map = new Map();

        players = new ArrayList<>();
        players.add(new Player());

        log = new Log();

        chat = new Chat();

        bank = new Resources();

        turnTracker = new TurnTracker();

        biggestArmy = 0;
        longestRoad = 0;
        winner = 0;
        revision = 0;
    }
}
