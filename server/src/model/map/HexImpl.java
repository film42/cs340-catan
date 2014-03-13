package model.map;

import model.JsonImpl;
import modelInterfaces.map.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexImpl extends JsonImpl implements modelInterfaces.map.Hex {

    private boolean isLand;
    private Location location;
    private List<Vertex> vertexes;
    private List<Edge> edges;

    public HexImpl() {
        isLand = false;
        location = new LocationImpl();

        vertexes = new ArrayList<Vertex>();
        vertexes.add(new VertexImpl());
        vertexes.add(new VertexImpl());
        vertexes.add(new VertexImpl());

        edges = new ArrayList<Edge>();
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
    }
}
