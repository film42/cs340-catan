package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import modelInterfaces.base.TurnTracker;
import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class DiscardCards extends Command {

    private CardDeck discardedCards;

    public CardDeck getDiscardedCards() {
        return discardedCards;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        Game game = gameInfo.getData();
        Player player = game.getPlayerByIndex(playerIndex);
        Resources resources = player.getResources();

        //check if if he/she is discarded state
        if (!player.isDiscarded())
            return;

        for (String type : discardedCards.TYPES) {
            int discardNum = discardedCards.getResourceByString(type);
            if (discardNum > 0 ) {
                if (resources.getResourceByString(type) >= discardNum) {
                    resources.setResourceByString(type, resources.getResourceByString(type) - discardNum);
                } else {
                    server.Server.log.severe("No enough resources to discard for " + type);
                }

            }
        }

        // if it is the last on discard the cards, set to robbing state
        TurnTracker tracker = game.getTurnTracker();
        if(game.isLastPlayerIndex(playerIndex)) {
            tracker.setStatus(TurnTracker.ROBBING);
            tracker.setCurrentTurn(0);
        } else {
            tracker.setCurrentTurn(tracker.getCurrentTurn() + 1);
        }
        
    }

}
