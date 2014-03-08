package model.map;

import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class Map extends Model {
    private HexGrid hexGrid;
    private int radius;
    private int numbers;
    private int ports;
    private Robber robber;

    public Map() {
        hexGrid = new HexGrid();
        robber = new Robber();

        radius = 0;
        numbers = 0;
        ports = 0;
    }
}
