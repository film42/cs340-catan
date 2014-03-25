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
        Deck oldDevCards = player.getOldDevCards();
        if(newDevCards.getMonument() > 0){ //this might need to change depending on how new and old dev cards are arranged.
            newDevCards.setMonument(newDevCards.getMonument()-1);
            player.setVictoryPoints(player.getVictoryPoints() + 1);
        }
        if(oldDevCards.getMonument() > 0){
            oldDevCards.setMonument(oldDevCards.getMonument()-1);
        }

        // Prevent additional dev card playing
        player.setPlayedDevCard(true);
    }
}
