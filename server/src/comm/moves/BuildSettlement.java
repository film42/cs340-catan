package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;

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

//        TurnTracker turnTracker = game.getTurnTracker();
//        turnTracker.setStatus(TurnTracker.ROLLING);

        // Increment user counter
//        game.incrementUserCounter(playerIndex);




        gameInfo.setData(game);
    }

}
