package comm.moves;

import java.io.IOException;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;

import modelInterfaces.base.Player;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Resources;
import modelInterfaces.base.Deck;
/**
 * Created by: film42 on: 3/13/14.
 */
public class YearOfPlenty extends Command {

    private String resource1;
    private String resource2;

    public String getResource1() {
        return resource1;
    }

    public String getResource2() {
        return resource2;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

         Game game = gameInfo.getData();
         Player player = game.getPlayerByIndex(playerIndex);
         Resources resources = player.getResources();

         addSource(resources, getResource1());
         addSource(resources, getResource2());
         Deck deck = player.getOldDevCards();
         deck.setYearOfPlenty(deck.getYearOfPlenty()-1);
         gameInfo.setData(game);

    }
    private void addSource(Resources resources, String resource){
        resources.setResourceByString(resource, resources.getResourceByString(resource) +1);
   }
}
