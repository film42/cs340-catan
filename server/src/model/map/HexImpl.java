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
        vertexes.add(new VertexImpl());
        vertexes.add(new VertexImpl());
        vertexes.add(new VertexImpl());


        edges = new ArrayList<Edge>();
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());

    }

    public boolean isLand() {
        return isLand;
    }

    public void setLand(boolean isLand) {
        this.isLand = isLand;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
