package modelInterfaces.map;

import comm.request.CreateGameRequest;
import model.map.HexGridImpl;
import model.map.PortImpl;
import model.map.RobberImpl;

import java.util.List;
import java.util.Set;

/**
 * Created by Jon George on 3/13/14.
 */
public interface Map {
    HexGridImpl getHexGrid();

    void setHexGrid(HexGridImpl hexGrid);

    int getRadius();

    void setRadius(int radius);

    int getNumbers();

    void setNumbers(int numbers);

    List<PortImpl> getPorts();

    void setPorts(List<PortImpl> ports);

    Robber getRobber();

    void setRobber(RobberImpl robber);

    void initMap(CreateGameRequest createGameRequest);

    void initPorts(boolean randomPorts);
}
