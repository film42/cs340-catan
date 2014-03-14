package model.map;

import model.JsonImpl;
import model.Model;
import modelInterfaces.map.VertexValue;

/**
 * Created by: film42 on: 3/7/14.
 */
public class VertexImpl extends JsonImpl implements modelInterfaces.map.Vertex {

    private VertexValue value;

    public VertexImpl() {
        value = new VertexValueImpl();
    }

    public VertexValue getValue(){
        return value;
    }

    public void setValue(VertexValue newValue){
        value = newValue;
    }
}
