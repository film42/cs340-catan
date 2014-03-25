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
        //add one to victory points
        Game game = gameInfo.getData();
        Player player = game.getPlayerByIndex(getPlayerIndex());
        Deck newDevCards = player.getNewDevCards();
		if (newDevCards.getMonument() > 0) {
            newDevCards.setMonument(newDevCards.getMonument()-1);
            player.setVictoryPoints(player.getVictoryPoints() + 1);
        }

        // Prevent additional dev card playing
        player.setPlayedDevCard(true);
    }
}
