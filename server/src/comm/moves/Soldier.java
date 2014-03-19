package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.Location;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Soldier extends Command {

    private int victimIndex;
    private Location robberSpot;

    public int getVictimIndex() {
        return victimIndex;
    }

    public Location getRobberSpot() {
        return robberSpot;
    }

    @Override
    public void execute(GameInfo game) throws IOException, InvalidCommandException {

    }
}
