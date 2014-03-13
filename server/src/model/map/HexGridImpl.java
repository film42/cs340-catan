package model.map;

import model.Model;
import modelInterfaces.map.Hex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class HexGridImpl extends Model implements modelInterfaces.map.HexGrid {

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

}
