package model.base;

import model.JsonImpl;
import model.Model;
import modelInterfaces.base.Resources;
import modelInterfaces.base.Deck;

/**
 * Created by: film42 on: 3/7/14.
 */
public class PlayerImpl extends JsonImpl implements modelInterfaces.base.Player {

    private int MAX_GAME_POINTS;
    private Resources resources;
    private Deck oldDevCards;
    private Deck newDevCards;
    private int roads;
    private int cities;
    private int settlements;
    private int soldiers;
    private int victoryPoints;
    private int monuments;
    private boolean longestRoad;
    private boolean largestArmy;
    private boolean playedDevCard;
    private boolean discarded;
    private int playerID;
    private int orderNumber;
    private String name;
    private String color;

    public PlayerImpl() {
        this.MAX_GAME_POINTS = 10;
        this.resources = new ResourcesImpl();
        this.oldDevCards = new DeckImpl();
        this.newDevCards = new DeckImpl();
        this.roads = 0;
        this.cities = 0;
        this.settlements = 0;
        this.soldiers = 0;
        this.victoryPoints = 0;
        this.monuments = 0;
        this.longestRoad = false;
        this.largestArmy = false;
        this.playedDevCard = false;
        this.discarded = false;
        this.playerID = 0;
        this.orderNumber = 0;
        this.name = "Sam";
        this.color = "red";
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Deck getOldDevCards() {
        return oldDevCards;
    }

    public void setOldDevCards(Deck oldDevCards) {
        this.oldDevCards = oldDevCards;
    }

    public Deck getNewDevCards() {
        return newDevCards;
    }

    public void setNewDevCards(Deck newDevCards) {
        this.newDevCards = newDevCards;
    }

    public int getRoads() {
        return roads;
    }

    public void setRoads(int roads) {
        this.roads = roads;
    }

    public int getCities() {
        return cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public int getSettlements() {
        return settlements;
    }

    public void setSettlements(int settlements) {
        this.settlements = settlements;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getMonuments() {
        return monuments;
    }

    public void setMonuments(int monuments) {
        this.monuments = monuments;
    }

    public boolean isLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(boolean longestRoad) {
        this.longestRoad = longestRoad;
    }

    public boolean isLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(boolean largestArmy) {
        this.largestArmy = largestArmy;
    }

    public boolean isPlayedDevCard() {
        return playedDevCard;
    }

    public void setPlayedDevCard(boolean playedDevCard) {
        this.playedDevCard = playedDevCard;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public void setDiscarded(boolean discarded) {
        this.discarded = discarded;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}