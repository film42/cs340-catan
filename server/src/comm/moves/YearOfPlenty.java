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

         addSource(resources, resource1);
         addSource(resources, resource2);
         gameInfo.setData(game);

    }
    private void addSource(Resources resources, String resource){

        //validate resource
        if (resources.getResourceByString(resource) < 0) {
            server.Server.log.severe("Unrecognized resource :" + resource);
        }
        else {
            resources.setResourceByString(resource, resources.getResourceByString(resource) +1);
        }
   }
}
