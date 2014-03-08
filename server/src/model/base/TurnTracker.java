package model.base;

import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class TurnTracker extends Model {

    private String status;
    private int currentTurn;

    public TurnTracker() {
        status = "Rolling";
        currentTurn = 0;
    }
}
