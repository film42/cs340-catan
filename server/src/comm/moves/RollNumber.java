package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/12/14.
 */
public class RollNumber extends Command {

    private int number;

    public int getNumber() {
        return number;
    }

    @Override
    public void execute(GameInfo game) throws IOException, InvalidCommandException {

    }
}
