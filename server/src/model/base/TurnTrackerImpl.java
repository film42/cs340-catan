package model.base;

import model.JsonImpl;

/**
 * Created by: film42 on: 3/7/14.
 */
public class TurnTrackerImpl extends JsonImpl implements modelInterfaces.base.TurnTracker {

    private String status;
    private int currentTurn;

    public TurnTrackerImpl() {
        status = "Rolling";
        currentTurn = 0;
    }
}
