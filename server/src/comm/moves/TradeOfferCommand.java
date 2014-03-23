package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;
import modelInterfaces.base.*;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class TradeOfferCommand extends Command {

    private CardDeck offer;

    private int receiver;

    public Resources getOffer() {
        return offer;
    }

    public int getReceiver() {
        return receiver;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        Game game = gameInfo.getData();

        TradeOffer tradeOffer = game.getTradeOffer();
        tradeOffer.setReceiver(getReceiver());
        tradeOffer.setSender(getPlayerIndex());
        tradeOffer.setResourceOffer(offer);

        gameInfo.setData(game);
    }
}
