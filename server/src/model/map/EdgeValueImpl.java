package model.map;

import model.JsonImpl;
import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class EdgeValueImpl extends JsonImpl implements modelInterfaces.map.EdgeValue {

    private int ownerID;

    public EdgeValueImpl() {
        ownerID = -1;
    }

    public int getOwnerID(){
        return ownerID;
    }

    public void setOwnerID(int newID){
        ownerID = newID;
    }
}
