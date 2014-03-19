package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class RoadBuilding extends Command {
    private VertexLocation spot1;
    private VertexLocation spot2;

    public VertexLocation getSpot1() {
        return spot1;
    }

    public VertexLocation getSpot2() {
        return spot2;
    }

    @Override
    public void execute(GameInfo game) throws IOException, InvalidCommandException {

    }
}
