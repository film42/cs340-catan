package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.TurnTracker;

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
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        Game game = gameInfo.getData();

        TurnTracker turnTracker = game.getTurnTracker();
        String status = turnTracker.getStatus();

        switch (status) {
            case TurnTracker.ROLLING: game = rolling(game); break;
            case TurnTracker.ROBBING: game = robbing(game); break;
            case TurnTracker.DISCARDING: game = discarding(game); break;
        }

        gameInfo.setData(game);
    }

    private Game robbing(Game game) {
        return null;
    }

    private Game rolling(Game game) {
        TurnTracker tracker = game.getTurnTracker();


        // Set and return
        game.setTurnTracker(tracker);
        return game;
    }

    private Game discarding(Game game) {
        return null;
    }

}
