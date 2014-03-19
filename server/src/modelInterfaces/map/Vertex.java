package modelInterfaces.map;

import model.map.VertexValueImpl;

/**
 * Created by Jon George on 3/13/14.
 */
public interface Vertex {
    VertexValue getValue();

    void setValue(VertexValueImpl newValue);
}
