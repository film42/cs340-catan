package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.*;
import modelInterfaces.map.Map;

import java.io.IOException;
import java.util.List;

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
        game = rolling(game);

        // TODO: Get use player.addResourceList to add the resources before this is done
        Map map = game.getMap();
        List<Resources> playerResources = map.getResourcesByNumber(number);
        for (int i = 0; i < playerResources.size() && i < game.getPlayers().size(); i++){
            game.getPlayerByIndex(i).addResourceList(playerResources.get(i));
        }
        
        gameInfo.setData(game);
    }

    private Game rolling(Game game) {
        TurnTracker tracker = game.getTurnTracker();

        switch (number) {
            case 7:
                if(game.playersRequireDiscarding()) {
                    tracker.setStatus(TurnTracker.DISCARDING);
                } else {
                    tracker.setStatus(TurnTracker.ROBBING);
                }
                break;
            default: tracker.setStatus(TurnTracker.PLAYING); break;
        }

        // Set and return
        game.setTurnTracker(tracker);
        return game;
    }

}


