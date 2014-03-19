package model.map;

import com.google.inject.Inject;
import model.JsonImpl;
import modelInterfaces.map.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexImpl extends JsonImpl implements modelInterfaces.map.Hex {

    private boolean isLand;
    private LocationImpl location;
    private List<Vertex> vertexes;
    private List<Edge> edges;
    private String landtype;


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

    @Override
    public boolean isLand() {
        return isLand;
    }

    @Override
    public String getLandType(){return landtype;}

    @Override
    public void setLandType(String landtype) {
        this.landtype = landtype;
    }

    @Override
    public LocationImpl getLocation() {
        return location;
    }

    @Override
    public void setLocation(LocationImpl location) {
        this.location = location;
    }

    @Override
    public List<Vertex> getVertexes() {
        return vertexes;
    }

    @Override
    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public Edge getEdge(int direction){
        return edges.get(direction);
    }

    @Override
    public Vertex getVertex(int direction){
        return vertexes.get(direction);
    }

    @Override
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
