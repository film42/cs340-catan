package modelInterfaces.map;

import java.util.List;

/**
 * Created by Jon George on 3/13/14.
 */
public interface Hex {
    boolean isLand();

    void setLand(boolean isLand);

    Location getLocation();

    void setLocation(Location location);

    List<Vertex> getVertexes();

    void setVertexes(List<Vertex> vertexes);

    List<Edge> getEdges();

    Edge getEdge(int direction);

    Vertex getVertex(int direction);

    void setEdges(List<Edge> edges);
}
