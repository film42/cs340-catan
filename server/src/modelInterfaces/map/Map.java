package modelInterfaces.map;

import comm.request.CreateGameRequest;

import java.util.List;
import java.util.Set;

/**
 * Created by Jon George on 3/13/14.
 */
public interface Map {
    HexGrid getHexGrid();

    void setHexGrid(HexGrid hexGrid);

    int getRadius();

    void setRadius(int radius);

    int getNumbers();

    void setNumbers(int numbers);

    List<Port> getPorts();

    void setPorts(List<Port> ports);

    Robber getRobber();

    void setRobber(Robber robber);

    void initMap(CreateGameRequest createGameRequest);

    void initPorts(boolean randomPorts);
}
