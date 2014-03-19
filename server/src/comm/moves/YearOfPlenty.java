package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class YearOfPlenty extends Command {

    private String resource1;
    private String resource2;

    public String getResource1() {
        return resource1;
    }

    public String getResource2() {
        return resource2;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

    }
}
