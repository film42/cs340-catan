package model.base;

import comm.request.CreateGameRequest;
import model.InjectorFactory;
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
import modelInterfaces.users.User;

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
        players.add(InjectorFactory.getInjector().getInstance(Player.class));

        log = new LogImpl();

        chat = new ChatImpl();

        bank = new ResourcesImpl();

        turnTracker = new TurnTrackerImpl();

        biggestArmy = 0;
        longestRoad = 0;
        winner = 0;
        revision = 0;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public Map getMap() {
        return map;
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public Log getLog() {
        return log;
    }

    @Override
    public void setLog(Log log) {
        this.log = log;
    }

    @Override
    public Chat getChat() {
        return chat;
    }

    @Override
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public Resources getBank() {
        return bank;
    }

    @Override
    public void setBank(Resources bank) {
        this.bank = bank;
    }

    @Override
    public TurnTracker getTurnTracker() {
        return turnTracker;
    }

    @Override
    public void setTurnTracker(TurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    @Override
    public int getBiggestArmy() {
        return biggestArmy;
    }

    @Override
    public void setBiggestArmy(int biggestArmy) {
        this.biggestArmy = biggestArmy;
    }

    @Override
    public int getLongestRoad() {
        return longestRoad;
    }

    @Override
    public void setLongestRoad(int longestRoad) {
        this.longestRoad = longestRoad;
    }

    @Override
    public int getWinner() {
        return winner;
    }

    @Override
    public void setWinner(int winner) {
        this.winner = winner;
    }

    @Override
    public int getRevision() {
        return revision;
    }

    @Override
    public void setRevision(int revision) {
        this.revision = revision;
    }

    @Override
    public void initGame(CreateGameRequest createGameRequest) {
        //TODO intilize the Game using the given parameters
        //these parameters need to go to the map.
        map.initMap(createGameRequest);
    }

    @Override
    public void addPlayer(User user, String color) {
        Player newPlayer = InjectorFactory.getInjector().getInstance(Player.class);
        newPlayer.setName(user.getName());
        newPlayer.setPlayerID(user.getId());
        newPlayer.setOrderNumber(players.size());
        players.add(newPlayer);
    }
}
