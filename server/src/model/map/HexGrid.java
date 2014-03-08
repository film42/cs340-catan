package model.map;

import model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexGrid extends Model {

    private List<List<Hex>> hexes;
    private List<Integer> offsets;
    private int radius;
    private int x0;
    private int y0;

    public HexGrid() {
        hexes = new ArrayList<>();
        List<Hex> hexes_2 = new ArrayList<>();
        hexes_2.add(new Hex());

        hexes.add(hexes_2);

        offsets = new ArrayList<>();
        offsets.add(1);

        radius = 0;
        x0 = 0;
        y0 = 0;
    }

}
