package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;

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
    public void execute() throws IOException, InvalidCommandException {

    }

}
