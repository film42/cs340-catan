package model.map;

import modelInterfaces.map.Location;

/**
 * Created by: film42 on: 3/22/14.
 */
public interface MapAccessor {

    public void addRoad(int playerIndex, Location location);

    public void addSettlement(int playerIndex, Location location);

    public void addCity(int playerIndex, Location location);

}
