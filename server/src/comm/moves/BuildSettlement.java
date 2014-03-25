package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import model.map.LocationImpl;
import model.map.VertexDirection;
import modelInterfaces.base.*;
import modelInterfaces.map.Direction;
import modelInterfaces.map.Location;
import server.Server;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class BuildSettlement extends Command {

    private boolean free;
    private VertexLocation vertexLocation;

    public boolean isFree() {
        return free;
    }

    public VertexLocation getVertexLocation() {
        return vertexLocation;
    }

    @Override
    public String getLogMessage() {
        return " built a settlement";
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        super.execute(gameInfo);
        Game game = gameInfo.getData();
        Player curPlayer = game.getPlayerByIndex(playerIndex);


        if(curPlayer.getSettlements() < 1){
            Server.log.warning("Attempted to buy road without road available");
            throw new InvalidCommandException("Attempted to buy road without road available");
        }
        // TODO: Let's make sure we update the banks resources
        curPlayer.buySettlement(isFree());
        // Add back to Bank Resources
        Resources bank = game.getBank();
        bank.incrementResourceByString(Resources.WOOD, 1);
        bank.incrementResourceByString(Resources.BRICK, 1);
        bank.incrementResourceByString(Resources.SHEEP, 1);
        bank.incrementResourceByString(Resources.WHEAT, 1);
        game.getMap().addSettlement(getPlayerIndex(),getVertexLocation());

        //Add resources if it is second round
        TurnTracker turnTracker = game.getTurnTracker();
        if (turnTracker.getStatus().equals(TurnTracker.SECOND_ROUND)) {
            Location loc = new LocationImpl(getVertexLocation().getX(), getVertexLocation().getY());
            Direction dir = new VertexDirection(getVertexLocation().getDirection());
            Resources resources = game.getMap().getResourcesAroundVertex(loc,dir);
            Resources currentResource = curPlayer.getResources();

            for (String type : Resources.TYPES) {
                currentResource.setResourceByString(type,
                        currentResource.getResourceByString(type)+resources.getResourceByString(type));

            }
        }



        gameInfo.setData(game);
    }

}
