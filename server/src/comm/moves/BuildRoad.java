package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import modelInterfaces.base.GameInfo;

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
    public void execute(GameInfo game) throws IOException, InvalidCommandException {

    }
}
