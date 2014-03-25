package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.Location;
import modelInterfaces.base.*;
import modelInterfaces.map.Robber;
import server.Server;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jon on 3/24/14.
 */
public class RobPlayer extends Command {
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

        Game game = gameInfo.getData();
        //change to playing phase from robbing phase
        if(game.getTurnTracker().getStatus() != TurnTracker.ROBBING){
            Server.log.severe("Rob Player called outside of robbing phase");
            return;
        }

        //move robber to robberspot
        Robber robber = game.getMap().getRobber();
        robber.setX(getRobberSpot().getX());
        robber.setY(getRobberSpot().getY());

        //steal one random resource from victim. (make sure they have at least one);
        Player victim = game.getPlayerByIndex(getVictimIndex());
        Player currentPlayer = game.getPlayerByIndex(getPlayerIndex());
        Soldier.stealResource(victim.getResources(), currentPlayer.getResources());

        game.getTurnTracker().setStatus(TurnTracker.PLAYING);
    }


}
