package model.base;

import model.JsonImpl;
import modelInterfaces.base.Deck;

/**
 * Created by: film42 on: 3/7/14.
 */
public class DeckImpl extends JsonImpl implements Deck {

	protected int yearOfPlenty;
	protected int monopoly;
	protected int soldier;
	protected int roadBuilding;
	protected int monument;

    public DeckImpl() {
        yearOfPlenty = 0;
        monopoly = 0;
        soldier = 0;
        roadBuilding = 0;
        monument = 0;
    }

    @Override
	public int getYearOfPlenty(){
        return yearOfPlenty;
    }

    @Override
	public void setYearOfPlenty(int newYearOfPlenty){
        yearOfPlenty = newYearOfPlenty;
    }

    @Override
	public int getMonopoly(){
        return monopoly;
    }

    @Override
	public void setMonopoly(int newMonopoly){
        monopoly = newMonopoly;
    }

    @Override
	public int getSoldier(){
        return soldier;
    }

    @Override
	public void setSoldier(int newSoldier){
        soldier = newSoldier;
    }

    @Override
	public int getRoadBuilding(){
        return roadBuilding;
    }

    @Override
	public void setRoadBuilding(int newRoadBuilding){
        roadBuilding = newRoadBuilding;
    }

    @Override
	public int getMonument(){
        return monument;
    }

    @Override
	public void setMonument(int newMonument){
        monument = newMonument;
    }
}
