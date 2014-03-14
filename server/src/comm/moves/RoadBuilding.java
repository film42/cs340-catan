package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.forms.Location;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class RoadBuilding extends Command {
    private Location spot1;
    private Location spot2;

    public Location getSpot1() {
        return spot1;
    }

    public Location getSpot2() {
        return spot2;
    }

    @Override
    public void execute() throws IOException, InvalidCommandException {

    }
}
