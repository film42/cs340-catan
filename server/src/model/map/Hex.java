package model.map;

import model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class Hex extends Model {

    private boolean isLand;
    private Location location;
    private List<Vertex> vertexes;
    private List<Edge> edges;

    public Hex() {
        isLand = false;
        location = new Location();

        vertexes = new ArrayList<>();
        vertexes.add(new Vertex());
        vertexes.add(new Vertex());
        vertexes.add(new Vertex());

        edges = new ArrayList<>();
        edges.add(new Edge());
        edges.add(new Edge());
        edges.add(new Edge());
    }
}
