package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Resources;
import modelInterfaces.base.Player;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class OfferTrade extends Command {

    private CardDeck offer;
    private int receiver;

    public CardDeck getOffer() {
        return offer;
    }

    public int getReceiver() {
        return receiver;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        Game game = gameInfo.getData();
        Player playerSender = game.getPlayerByIndex(playerIndex);
        Player playerReceiver = game.getPlayerByIndex(receiver);

        //check if trade is will to accept
        if ( !playerReceiver.isWillAcceptTrade())
            return;

        Resources resourcesSender = playerSender.getResources();
        Resources resourcesReceiver = playerReceiver.getResources();

        for (String type : offer.TYPES) {

            int num = offer.getResourceByString(type);
            if(num > 0){ // resource offered

                resourcesSender.setResourceByString(type, (resourcesSender.getResourceByString(type) - num));
                resourcesReceiver.setResourceByString(type,(resourcesReceiver.getResourceByString(type) + num));
            }

            else if (offer.getBrick() < 0){ //resource asked for
                resourcesSender.setResourceByString(type,(resourcesSender.getResourceByString(type) + Math.abs(num)));
                resourcesReceiver.setResourceByString(type,(resourcesReceiver.getResourceByString(type) - Math.abs(num)));
            }
        }
        playerReceiver.setWillAcceptTrade(false);
        gameInfo.setData(game);
    }
}
