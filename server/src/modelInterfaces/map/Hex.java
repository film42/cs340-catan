package modelInterfaces.map;

import model.map.EdgeImpl;
import model.map.LocationImpl;
import model.map.VertexImpl;

import java.util.List;

/**
 * Created by Jon George on 3/13/14.
 */
public interface Hex {
    boolean isLand();

    void setLand(String landtype);

    String getLandType();

    LocationImpl getLocation();

    void setLocation(LocationImpl location);

    List<Vertex> getVertexes();

    void setVertexes(List<Vertex> vertexes);

    List<Edge> getEdges();

    Edge getEdge(int direction);

    Vertex getVertex(int direction);

    void setEdges(List<Edge> edges);
}
