package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.GameInfo;

import java.io.IOException;

/**
 * Created by: film42 on: 3/12/14.
 */
public class SendChat extends Command {

    private String content;

    public String getContent() {
        return content;
    }

    @Override
    public void execute(GameInfo game) throws IOException, InvalidCommandException {

    }
}
