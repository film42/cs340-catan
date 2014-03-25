package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import server.Server;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class BuildRoad extends Command {
    private boolean free;
    private VertexLocation roadLocation;

    public boolean isFree() {
        return free;
    }

    public VertexLocation getRoadLocation() {
        return roadLocation;
    }

    @Override
    public String getLogMessage() {
        return " built a road";
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        super.execute(gameInfo);
        Game game = gameInfo.getData();
        Player curPlayer = game.getPlayerByIndex(playerIndex);
        if(curPlayer.getRoads() < 1){
            Server.log.warning("Attempted to buy road without road available");
            throw new InvalidCommandException("Attempted to buy road without road available");
        }
        // TODO: Let's make sure we update the banks resources
        curPlayer.buyRoad(isFree());
        game.getMap().addRoad(getPlayerIndex(),getRoadLocation());

        // Add back to Bank Resources
        Resources bank = game.getBank();
        bank.incrementResourceByString(Resources.WOOD, 1);
        bank.incrementResourceByString(Resources.BRICK, 1);

        // Update Longest Road
        game.determineLongestRoad();
    }
}
