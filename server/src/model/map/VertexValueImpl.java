package model.map;

import model.JsonImpl;
import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class VertexValueImpl extends JsonImpl implements modelInterfaces.map.VertexValue {

    private int worth;
    private int ownerID;

    public VertexValueImpl() {
        worth = 0;
        ownerID = -1;
    }
}
