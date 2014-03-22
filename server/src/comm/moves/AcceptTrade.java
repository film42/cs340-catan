package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;

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
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        Game game = gameInfo.getData();
        Player player = game.getPlayerByIndex(playerIndex);
        player.setWillAcceptTrade(willAccept);
        gameInfo.setData(game);
    }

}
