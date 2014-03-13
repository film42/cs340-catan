package model.map;

import model.Model;
import modelInterfaces.map.EdgeValue;

/**
 * Created by: film42 on: 3/7/14.
 */
public class EdgeImpl extends Model implements modelInterfaces.map.Edge {

    private EdgeValue value;

    public EdgeImpl() {
        value = new EdgeValueImpl();
    }
}
