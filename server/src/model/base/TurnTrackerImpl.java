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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
