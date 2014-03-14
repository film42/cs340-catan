package model.base;

import model.JsonImpl;
import modelInterfaces.base.Deck;

/**
 * Created by: film42 on: 3/7/14.
 */
public class DeckImpl extends JsonImpl implements Deck {

    private int yearOfPlenty;
    private int monopoly;
    private int soldier;
    private int roadBuilding;
    private int monument;

    public DeckImpl() {
        yearOfPlenty = 0;
        monopoly = 0;
        soldier = 0;
        roadBuilding = 0;
        monument = 0;
    }

    public int getYearOfPlenty(){
        return yearOfPlenty;
    }

    public void setYearOfPlenty(int newYearOfPlenty){
        yearOfPlenty = newYearOfPlenty;
    }

    public int getMonopoly(){
        return monopoly;
    }

    public void setMonopoly(int newMonopoly){
        monopoly = newMonopoly;
    }

    public int getSoldier(){
        return soldier;
    }

    public void setSoldier(int newSoldier){
        soldier = newSoldier;
    }

    public int getRoadBuilding(){
        return roadBuilding;
    }

    public void setRoadBuilding(int newRoadBuilding){
        roadBuilding = newRoadBuilding;
    }

    public int getMonument(){
        return monument;
    }

    public void setMonument(int newMonument){
        monument = newMonument;
    }
}
