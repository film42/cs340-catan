package model.map;

import model.JsonImpl;
import model.Model;
import modelInterfaces.map.EdgeValue;

/**
 * Created by: film42 on: 3/7/14.
 */
public class EdgeImpl extends JsonImpl implements modelInterfaces.map.Edge {

    private EdgeValue value;

    public EdgeImpl() {
        value = new EdgeValueImpl();
    }

    public EdgeValue getValue(){
        return value;
    }

    public void setValue(EdgeValue newValue){
        value = newValue;
    }
}
