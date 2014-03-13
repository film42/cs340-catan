package model.map;

import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class EdgeValueImpl extends Model implements modelInterfaces.map.EdgeValue {

    private int ownerID;

    public EdgeValueImpl() {
        ownerID = -1;
    }
}
