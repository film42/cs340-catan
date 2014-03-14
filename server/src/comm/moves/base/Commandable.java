package comm.moves.base;

import java.io.IOException;

/**
 * Created by: film42 on: 3/12/14.
 */
public interface Commandable {

    public void execute() throws IOException, InvalidCommandException;

}
