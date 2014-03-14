package model.base;

import model.JsonImpl;
import model.map.MapImpl;
import model.messaging.ChatImpl;
import model.messaging.LogImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import modelInterfaces.base.TurnTracker;
import modelInterfaces.base.Deck;
import modelInterfaces.map.Map;
import modelInterfaces.messaging.Chat;
import modelInterfaces.messaging.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/6/14.
 */
public class GameImpl extends JsonImpl implements Game {
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

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Resources getBank() {
        return bank;
    }

    public void setBank(Resources bank) {
        this.bank = bank;
    }

    public TurnTracker getTurnTracker() {
        return turnTracker;
    }

    public void setTurnTracker(TurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    public int getBiggestArmy() {
        return biggestArmy;
    }

    public void setBiggestArmy(int biggestArmy) {
        this.biggestArmy = biggestArmy;
    }

    public int getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(int longestRoad) {
        this.longestRoad = longestRoad;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
