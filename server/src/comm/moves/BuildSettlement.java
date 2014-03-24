package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
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
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        Game game = gameInfo.getData();
        Player curPlayer = game.getPlayerByIndex(playerIndex);
        if(curPlayer.getSettlements() < 1){
            Server.log.warning("Attempted to buy road without road available");
            throw new InvalidCommandException("Attempted to buy road without road available");
        }
        curPlayer.buySettlement(isFree());
        game.getMap().addSettlement(getPlayerIndex(),getVertexLocation());
        gameInfo.setData(game);
    }

}
