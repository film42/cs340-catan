package model.map;

import model.JsonImpl;
import modelInterfaces.map.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexGridImpl extends JsonImpl implements modelInterfaces.map.HexGrid {

    private List<List<HexImpl>> hexes;
    private List<Integer> offsets;
    private int radius;
    private int x0;
    private int y0;

    public HexGridImpl() {
        hexes = new ArrayList<>();
        List<HexImpl> hexes_2 = new ArrayList<>();
        hexes_2.add(new HexImpl());

        hexes.add(hexes_2);

        offsets = new ArrayList<>();
        offsets.add(3);
        offsets.add(2);
        offsets.add(1);
        offsets.add(0);
        offsets.add(0);
        offsets.add(0);
        offsets.add(0);


        radius = 0;
        x0 = 0;
        y0 = 0;
    }


    //needed? maybe not...this could just go in getHex since it is never called outside of there....
    public Location internalHexLocation(Location hexLoc){
        int translatedX = hexLoc.getX() + x0;
        int translatedY = hexLoc.getY() + y0;
        int arrayX = translatedX - offsets.get(translatedY);
        int arrayY = translatedY;
        Location loc = new LocationImpl();
        loc.setX(arrayX);
        loc.setY(arrayY);
        return loc;
    }


    public Hex getHex(Location hexLoc){
        int translatedX = hexLoc.getX() + x0;
        int translatedY = hexLoc.getY() + y0;
        int arrayX = translatedX - offsets.get(translatedY);
        int arrayY = translatedY;

        if(arrayY >= hexes.size() || arrayY < 0){
            return null;
        }
        else{
            if(arrayX < 0 || arrayX >= hexes.get(arrayY).size()){
                return null;
            }
            else{
                return hexes.get(arrayY).get(arrayX);
            }
        }
    }

    public void addRoad(Location hexLocation, Direction direction, int playerIndex){
        //get hexes
        Hex hex = getHex(hexLocation);
        Location neighborLoc = hexLocation.getNeighborLocation(direction.getNumDirection());
        Hex neighborHex = getHex(neighborLoc);
        if(hex != null){
            Edge edge = hex.getEdge(direction.getNumDirection());
            EdgeValue edgevalue = edge.getValue();
            edgevalue.setOwnerID(playerIndex);

        }
        if(neighborHex != null){
            Edge neighborEdge = neighborHex.getEdge(direction.getOppositeDirection());
            EdgeValue neighborEdgeValue = neighborEdge.getValue();
            neighborEdgeValue.setOwnerID(playerIndex);
        }

    }


    public void addSettlement(Location hexLocation, Direction direction, int playerIndex){
        Hex hex = getHex(hexLocation);
        Location neighborLoc = hexLocation.getNeighborLocation(direction.getNumDirection());
        Hex neighborHex = getHex(neighborLoc);
        Location ccwNeighborLoc = hexLocation.getNeighborLocation(direction.nextDirectionCounterClockwise());
        Hex ccwNeighborHex = getHex(neighborLoc);
        if(hex != null){
            Vertex vertex = hex.getVertex(direction.getNumDirection());
            VertexValue vertexvalue = vertex.getValue();
            vertexvalue.setOwnerID(playerIndex);
            vertexvalue.setWorth(1);
        }
        if(neighborHex != null){
            Vertex vertex = neighborHex.getVertex(direction.nextDoubleCounterClockwise());
            VertexValue vertexvalue = vertex.getValue();
            vertexvalue.setOwnerID(playerIndex);
            vertexvalue.setWorth(1);
        }
        if(ccwNeighborHex != null){
            Vertex vertex = ccwNeighborHex.getVertex(direction.nextDoubleClockwise());
            VertexValue vertexvalue = vertex.getValue();
            vertexvalue.setOwnerID(playerIndex);
            vertexvalue.setWorth(1);
        }
    }


    public void addCity(Location hexLocation, Direction direction, int playerIndex){
        Hex hex = getHex(hexLocation);
        Location neighborLoc = hexLocation.getNeighborLocation(direction.getNumDirection());
        Hex neighborHex = getHex(neighborLoc);
        Location ccwNeighborLoc = hexLocation.getNeighborLocation(direction.nextDirectionCounterClockwise());
        Hex ccwNeighborHex = getHex(neighborLoc);
        if(hex != null){
            Vertex vertex = hex.getVertex(direction.getNumDirection());
            VertexValue vertexvalue = vertex.getValue();
            vertexvalue.setOwnerID(playerIndex);
            vertexvalue.setWorth(2);
        }
        if(neighborHex != null){
            Vertex vertex = neighborHex.getVertex(direction.nextDoubleCounterClockwise());
            VertexValue vertexvalue = vertex.getValue();
            vertexvalue.setOwnerID(playerIndex);
            vertexvalue.setWorth(2);
        }
        if(ccwNeighborHex != null){
            Vertex vertex = ccwNeighborHex.getVertex(direction.nextDoubleClockwise());
            VertexValue vertexvalue = vertex.getValue();
            vertexvalue.setOwnerID(playerIndex);
            vertexvalue.setWorth(2);
        }
    }


    public List<List<HexImpl>> getHexes() {
        return hexes;
    }


    public void setHexes(List<List<HexImpl>> hexes) {
        this.hexes = hexes;
    }


    public List<Integer> getOffsets() {
        return offsets;
    }


    public void setOffsets(List<Integer> offsets) {
        this.offsets = offsets;
    }


    public int getRadius() {
        return radius;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }


    public int getX0() {
        return x0;
    }


    public void setX0(int x0) {
        this.x0 = x0;
    }


    public int getY0() {
        return y0;
    }


    public void setY0(int y0) {
        this.y0 = y0;
    }
}
