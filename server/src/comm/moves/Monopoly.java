package comm.moves;

import java.io.IOException;

import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Monopoly extends Command {

    private String resource;

    public String getResource() {
        return resource;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

		// Use monopoly card

		// Get the game model
		Game game = gameInfo.getData();

		// Cycle through all players, steal a card, increase count, and skip if it's the current player
		int cardsStolen = 0;
		for (Player p : game.getPlayers()) {
			if (p.getOrderNumber() != this.playerIndex) {
				// if p.getResources()
			}
		}
    }
}
