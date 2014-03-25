package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import modelInterfaces.base.Deck;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class BuyDevCard extends Command {
    @Override
    public String getLogMessage() {
        return " bought a development card";
    }

    @Override
	public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        super.execute(gameInfo);
        Game game = gameInfo.getData();
		Deck deck = game.getDeck();

		if (deck.getDeckCount() <= 0) {
			server.Server.log.severe("No Development Card Type in the Deck");
			return;
		}
		Player player = game.getPlayerByIndex(playerIndex);
		Deck newDevDeck = player.getNewDevCards();

		String type = deck.getDevCard();
		switch (type) {

		case Deck.YEAR_OF_PLENTY:
			newDevDeck.setYearOfPlenty(newDevDeck.getYearOfPlenty() + 1);
			break;
		case Deck.MONOPOLY:
			newDevDeck.setMonopoly(newDevDeck.getMonopoly() + 1);
			break;
		case Deck.SOLDIER:
			newDevDeck.setSoldier(newDevDeck.getSoldier() + 1);
			break;
		case Deck.ROAD_BUILDING:
			newDevDeck.setRoadBuilding(newDevDeck.getRoadBuilding() + 1);
			break;
		case Deck.MONUMENT:
			newDevDeck.setMonument(newDevDeck.getMonument() + 1);
			break;
		default:
			server.Server.log.severe("Invalid Development Card Type " + type);
			break;
		}
        player.buyDevCard();
		gameInfo.setData(game);
	}
}
