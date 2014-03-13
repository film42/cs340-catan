package model.map;

import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class RobberImpl extends Model implements modelInterfaces.map.Robber {

    private int x;
    private int y;

    public RobberImpl() {
        // TODO: This is code duplication, but easier to serialize when duplicated.
        x = 0;
        y = 0;
    }

}
