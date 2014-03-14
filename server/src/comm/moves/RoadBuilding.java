package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.forms.HexLocation;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class RoadBuilding extends Command {
    private HexLocation spot1;
    private HexLocation spot2;

    public HexLocation getSpot1() {
        return spot1;
    }

    public HexLocation getSpot2() {
        return spot2;
    }

    @Override
    public void execute() throws IOException, InvalidCommandException {

    }
}
