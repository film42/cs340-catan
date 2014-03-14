package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;

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
    public void execute() throws IOException, InvalidCommandException {

    }
}
