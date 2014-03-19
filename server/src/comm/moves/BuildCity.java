package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class BuildCity extends Command {

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

    }

}
