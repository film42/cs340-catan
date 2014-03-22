package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Game;
import modelInterfaces.base.Player;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Monument extends Command {
    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        //add one to victory points
        Game game = gameInfo.getData();
        Player player = game.getPlayerByIndex(getPlayerIndex());
        player.setVictoryPoints(player.getVictoryPoints()+1);
        gameInfo.setData(game);
    }
}
