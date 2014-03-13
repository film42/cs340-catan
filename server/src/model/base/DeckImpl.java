package model.base;

import model.Model;
import modelInterfaces.base.Deck;

/**
 * Created by: film42 on: 3/7/14.
 */
public class DeckImpl extends Model implements Deck {

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

}
