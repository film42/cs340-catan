package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.*;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Monopoly extends Command {

	private String resource;

	public String getResource() {
		return resource;
	}

    @Override
    public String getLogMessage() {
        return " played a monopoly card";
    }

    @Override
	public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        super.execute(gameInfo);
        // Get the game model
		Game game = gameInfo.getData();

		// Cycle through all players, steal a card, increase count, and skip if it's the current player
		int cardsStolen = 0;
		for (Player p : game.getPlayers()) {
            if (p.getOrderNumber() != this.playerIndex) {
                int resourceNum = p.getResources().getResourceByString(resource.toLowerCase());
                p.getResources().setResourceByString(resource.toLowerCase(), 0);
                cardsStolen+=resourceNum;

            }
        }
		if (cardsStolen > 0) {
            // give the qty of stolen cards to the user
            int oldQty = 0;
            Player curPlayer = game.getPlayerByIndex(playerIndex);
           
            curPlayer.getResources().incrementResourceByString(resource.toLowerCase(), cardsStolen);
			Deck deck = game.getPlayerByIndex(playerIndex).getNewDevCards();
            deck.setMonopoly(deck.getMonopoly()-1);
        }

        // Played Dev Card
        game.getPlayerByIndex(playerIndex).setPlayedDevCard(true);

        // Write our changes
        gameInfo.setData(game);
	}
}
