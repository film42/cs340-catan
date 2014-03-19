package model.map;

import comm.request.CreateGameRequest;
import model.JsonImpl;
import modelInterfaces.map.HexGrid;
import modelInterfaces.map.Map;
import modelInterfaces.map.Port;
import modelInterfaces.map.Robber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
/**
 * Created by: film42 on: 3/7/14.
 */
public class MapImpl extends JsonImpl implements modelInterfaces.map.Map {
    private HexGridImpl hexGrid;
    private int radius;
    private int numbers;
    private List<PortImpl> ports;
    private RobberImpl robber;


    public MapImpl() {
        radius = 0;
        numbers = 0;
        this.robber = new RobberImpl();
    }
    public HexGridImpl getHexGrid() {
        return hexGrid;
    }

    @Override
    public void setHexGrid(HexGridImpl hexGrid) {
        this.hexGrid = hexGrid;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getNumbers() {
        return numbers;
    }

    @Override
    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<PortImpl> getPorts() {
        return ports;
    }

    @Override
    public void setPorts(List<PortImpl> ports) {
        this.ports = ports;
    }

    @Override
    public Robber getRobber() {
        return robber;
    }

    @Override
    public void setRobber(RobberImpl robber) {
        this.robber = robber;
    }

    @Override
    public void initMap(CreateGameRequest createGameRequest) {
        //TODO create the map using these parameters
    }

    //final int[][] PortLocations = [[-3,0],[-3,2],[],[],[],[],[], [], []]
    //final String[][] validVertex1 = [["E",-3,0],[],[],[],[],[],[], [], []]
    //final String[] PortRatios = ["","Wood",]



    @Override
    public void initPorts(boolean randomPorts){

    }

}
