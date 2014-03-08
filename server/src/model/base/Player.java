package model.base;

import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class Player extends Model {

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

    public Player() {
        this.MAX_GAME_POINTS = 10;
        this.resources = new Resources();
        this.oldDevCards = new Deck();
        this.newDevCards = new Deck();
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
}