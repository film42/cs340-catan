package comm.moves;

import comm.generic.BaseRequest;

import java.io.IOException;

/**
 * Created by: film42 on: 3/12/14.
 */
public class Command extends BaseRequest implements Commandable {

    private String type;

    private int playerIndex;

    public String getType() {
        return type;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    @Override
    public void execute() throws IOException {

    }
}
