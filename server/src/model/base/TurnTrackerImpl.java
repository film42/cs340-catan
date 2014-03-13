package model.base;

import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class TurnTrackerImpl extends Model implements modelInterfaces.base.TurnTracker {

    private String status;
    private int currentTurn;

    public TurnTrackerImpl() {
        status = "Rolling";
        currentTurn = 0;
    }
}
