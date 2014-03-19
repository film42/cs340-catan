package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Monopoly extends Command {

    private String resource;

    public String getResource() {
        return resource;
    }

    @Override
    public void execute(GameInfo game) throws IOException, InvalidCommandException {

    }
}
