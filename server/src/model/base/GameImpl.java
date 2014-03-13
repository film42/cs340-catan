package model.base;

import model.Model;
import model.map.MapImpl;
import model.messaging.ChatImpl;
import model.messaging.LogImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import modelInterfaces.base.TurnTracker;
import modelInterfaces.map.Map;
import modelInterfaces.messaging.Chat;
import modelInterfaces.messaging.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/6/14.
 */
public class GameImpl extends Model implements Game {
    private DeckImpl deck;
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

    public GameImpl() {
        deck = new DeckImpl();

        map = new MapImpl();

        players = new ArrayList<Player>();
        players.add(new PlayerImpl());

        log = new LogImpl();

        chat = new ChatImpl();

        bank = new ResourcesImpl();

        turnTracker = new TurnTrackerImpl();

        biggestArmy = 0;
        longestRoad = 0;
        winner = 0;
        revision = 0;
    }
}
