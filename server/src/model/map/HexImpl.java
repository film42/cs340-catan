package model.map;

import model.Model;
import modelInterfaces.map.Edge;
import modelInterfaces.map.Location;
import modelInterfaces.map.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexImpl extends Model implements modelInterfaces.map.Hex {

    private boolean isLand;
    private Location location;
    private List<Vertex> vertexes;
    private List<Edge> edges;

    public HexImpl() {
        isLand = false;
        location = new LocationImpl();

        vertexes = new ArrayList<VertexImpl>();
        vertexes.add(new VertexImpl());
        vertexes.add(new VertexImpl());
        vertexes.add(new VertexImpl());

        edges = new ArrayList<EdgeImpl>();
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
        edges.add(new EdgeImpl());
    }
}
