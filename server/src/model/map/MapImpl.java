package model.map;

import model.JsonImpl;
import model.Model;
import modelInterfaces.map.HexGrid;
import modelInterfaces.map.Robber;

/**
 * Created by: film42 on: 3/7/14.
 */
public class MapImpl extends JsonImpl implements modelInterfaces.map.Map {
    private HexGrid hexGrid;
    private int radius;
    private int numbers;
    private int ports;
    private Robber robber;

    public MapImpl() {
        hexGrid = new HexGridImpl();
        robber = new RobberImpl();

        radius = 0;
        numbers = 0;
        ports = 0;
    }

    public HexGrid getHexGrid() {
        return hexGrid;
    }

    public void setHexGrid(HexGrid hexGrid) {
        this.hexGrid = hexGrid;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getPorts() {
        return ports;
    }

    public void setPorts(int ports) {
        this.ports = ports;
    }

    public Robber getRobber() {
        return robber;
    }

    public void setRobber(Robber robber) {
        this.robber = robber;
    }
}
