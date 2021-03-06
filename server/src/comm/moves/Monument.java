package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Deck;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Game;
import modelInterfaces.base.Player;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Monument extends Command {
    @Override
    public String getLogMessage() {
        return " played a monument";
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        super.execute(gameInfo);
        Game game = gameInfo.getData();
        Player player = game.getPlayerByIndex(getPlayerIndex());

		if (player.getNewDevCards().getMonument() == 0)
			throw new InvalidCommandException("Player does not have a Monument Card to play");

		// add one to victory points
		player.setVictoryPoints(player.getVictoryPoints() + 1);
		gameInfo.setData(game);

		// Reduce monument card count
		Deck newCards = player.getNewDevCards();
		newCards.setMonument(newCards.getMonument() - 1);
		Deck oldCards = player.getOldDevCards();
		oldCards.setMonument(oldCards.getMonument() - 1);

        // Prevent additional dev card playing
        player.setPlayedDevCard(true);
    }
}
