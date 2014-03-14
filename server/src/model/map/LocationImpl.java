package model.map;

/**
 * Created by: film42 on: 3/7/14.
 */
public class LocationImpl implements modelInterfaces.map.Location {

    private int x;
    private int y;

    public LocationImpl() {
        x = 0;
        y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
