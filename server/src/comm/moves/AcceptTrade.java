package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class AcceptTrade extends Command {

    private boolean willAccept;

    public boolean willAccept() {
        return willAccept;
    }

    @Override
    public void execute() throws IOException, InvalidCommandException {

    }

}
