package model.map;

import model.Model;
import modelInterfaces.map.HexGrid;
import modelInterfaces.map.Robber;

/**
 * Created by: film42 on: 3/7/14.
 */
public class MapImpl extends Model implements modelInterfaces.map.Map {
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
}
