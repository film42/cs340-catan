package model.base;

import java.util.List;
import com.google.inject.Inject;
import comm.moves.YearOfPlenty;
import model.JsonImpl;
import modelInterfaces.base.Deck;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by: film42 on: 3/7/14.
 */
public class DeckImpl extends JsonImpl implements Deck {

    protected int yearOfPlenty;
    protected int monopoly;
    protected int soldier;
    protected int roadBuilding;
    protected int monument;


    @Inject
    public DeckImpl() {
        yearOfPlenty = 2;
        monopoly = 2;
        soldier = 14;
        roadBuilding = 2;
        monument = 5;
    }

    @Override
    public int getYearOfPlenty() {
        return yearOfPlenty;
    }

    @Override
    public void setYearOfPlenty(int newYearOfPlenty) {
        yearOfPlenty = newYearOfPlenty;
    }

    @Override
    public int getMonopoly() {
        return monopoly;
    }

    @Override
    public void setMonopoly(int newMonopoly) {
        monopoly = newMonopoly;
    }

    @Override
    public int getSoldier() {
        return soldier;
    }

    @Override
    public void setSoldier(int newSoldier) {
        soldier = newSoldier;
    }

    @Override
    public int getRoadBuilding() {
        return roadBuilding;
    }

    @Override
    public void setRoadBuilding(int newRoadBuilding) {
        roadBuilding = newRoadBuilding;
    }

    @Override
    public int getMonument() {
        return monument;
    }

    @Override
    public Deck clone() {
        Deck ret = new DeckImpl();
        ret.setMonument(this.getMonument());
        ret.setMonopoly(this.getMonopoly());
        ret.setRoadBuilding(this.getRoadBuilding());
        ret.setSoldier(this.getSoldier());
        ret.setYearOfPlenty(this.getYearOfPlenty());
        return ret;
    }

    @Override
    public void setMonument(int newMonument) {
        monument = newMonument;
    }

    @Override
    public int getDeckCount() {
        return (yearOfPlenty + monopoly + soldier + roadBuilding + monument);
    }
    @Override
    public String getDevCard(){
        List<String> haveCards = new ArrayList<>();
        for (String type : TYPES) {

            switch (type) {
                case YEAR_OF_PLENTY:
                    if (yearOfPlenty > 0)
                        haveCards.add(type);
                    break;
                case MONOPOLY:
                    if (monopoly > 0)
                        haveCards.add(type);
                    break;
                case SOLDIER:
                    if (soldier > 0)
                        haveCards.add(type);
                    break;
                case ROAD_BUILDING:
                    if (roadBuilding > 0)
                        haveCards.add(type);
                    break;
                case MONUMENT:
                    if (monument > 0) ;
                    haveCards.add(type);
                    break;
                default:
                    server.Server.log.severe("Invalid Development Card Type " + type);
                    return Deck.INVALID_DECK;
            }
        }
        if (haveCards.size() < 1)
            return Deck.INVALID_DECK;

        int index = (int) (Math.random() * haveCards.size());
        String type = haveCards.get(index);

        switch (type) {
            case YEAR_OF_PLENTY:
                yearOfPlenty--;
                break;
            case MONOPOLY:
                monopoly--;
                break;
            case SOLDIER:
                soldier--;
                break;
            case ROAD_BUILDING:
                roadBuilding--;
                break;
            case MONUMENT:
                monument-- ;
                break;
            default:
                server.Server.log.severe("Invalid Development Card Type " + type);
                return Deck.INVALID_DECK;
        }
        return type;
    }

 }
