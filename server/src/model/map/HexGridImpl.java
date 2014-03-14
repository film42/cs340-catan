package model.map;

import model.JsonImpl;
import model.Model;
import modelInterfaces.map.Hex;
import modelInterfaces.map.Direction;
import modelInterfaces.map.Location;
import modelInterfaces.base.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexGridImpl extends JsonImpl implements modelInterfaces.map.HexGrid {

    private List<List<Hex>> hexes;
    private List<Integer> offsets;
    private int radius;
    private int x0;
    private int y0;

    public HexGridImpl() {
        hexes = new ArrayList<List<Hex>>();
        List<Hex> hexes_2 = new ArrayList<Hex>();
        hexes_2.add(new HexImpl());

        hexes.add(hexes_2);

        offsets = new ArrayList<Integer>();
        offsets.add(1);

        radius = 0;
        x0 = 0;
        y0 = 0;
    }

    public void addRoad(Location hexLocation, Direction direction, Player player){

    }

    public void addSettlement(Location hexLocation, Direction direction, Player player){

    }

    public void addCity(Location hexLocation, Direction direction, Player player){

    }

    public List<List<Hex>> getHexes() {
        return hexes;
    }

    public void setHexes(List<List<Hex>> hexes) {
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
