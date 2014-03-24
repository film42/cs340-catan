package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import model.map.MapImpl;
import modelInterfaces.base.*;
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
        Player curPlayer = game.getPlayers().get(getPlayerIndex());
        if(curPlayer.getRoads() < 1){
            Server.log.warning("Attempted to buy road without road available");
            return;
        }
        curPlayer.buyRoad(isFree());
        game.getMap().addRoad(getPlayerIndex(),getVertexLocation());
    }

}
