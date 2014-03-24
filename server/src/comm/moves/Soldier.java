package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.Location;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import modelInterfaces.map.Robber;


import java.io.IOException;
import java.util.*;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Soldier extends Command {

    private int victimIndex;
    private Location location;

    public int getVictimIndex() {
        return victimIndex;
    }

    public Location getRobberSpot() {
        return location;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        //move robber to robberspot
        Game game = gameInfo.getData();
        Robber robber = game.getMap().getRobber();
        robber.setX(getRobberSpot().getX());
        robber.setY(getRobberSpot().getY());

        //steal one random resource from victim. (make sure they have at least one);
        Player victim = game.getPlayerByIndex(getVictimIndex());
        Player currentPlayer = game.getPlayerByIndex(getPlayerIndex());
        stealResource(victim.getResources(), currentPlayer.getResources());

    }

    private void stealResource(Resources stealFrom, Resources giveTo){
        List<String> availibleList = stealFrom.getAvailibleResources();
        if(availibleList.size() <=0){
            System.err.println("Steal Resource called on player with no resources");
            return;
        }

        int index = (int)(Math.random()*availibleList.size());
        String type = availibleList.get(index);
        stealFrom.setResourceByString(type, stealFrom.getResourceByString(type) - 1);
        giveTo.setResourceByString(type, giveTo.getResourceByString(type)+1);


    }
}
