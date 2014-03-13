package model.map;

import model.Model;
import modelInterfaces.map.VertexValue;

/**
 * Created by: film42 on: 3/7/14.
 */
public class VertexImpl extends Model implements modelInterfaces.map.Vertex {

    private VertexValue value;

    public VertexImpl() {
        value = new VertexValueImpl();
    }
}
