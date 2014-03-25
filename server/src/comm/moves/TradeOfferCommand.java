package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;
import model.InjectorFactory;
import model.ModelModule;
import model.base.TradeOfferImpl;
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

		System.out.println("> " + getPlayerIndex() + ", " + getReceiver());
        Game game = gameInfo.getData();
		TradeOffer newTradeOffer = InjectorFactory.getInjector().getInstance(TradeOffer.class);
		newTradeOffer.setReceiver(getReceiver());
		System.out.println(">>> " + newTradeOffer.getReceiver());
		newTradeOffer.setSender(getPlayerIndex());
		newTradeOffer.setResourceOffer(offer);
		game.setTradeOffer(newTradeOffer);

        gameInfo.setData(game);
		System.out.println(">> " + gameInfo.getData().getTradeOffer().getReceiver());
    }
}
