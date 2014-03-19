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
         addSource(player, resource1);
         addSource(player, resource2);

    }
    private void addSource(Player p, String resource){
        switch (resource) {
            case Resources.WOOD:
                p.getResources().setWood(p.getResources().getWood() + 1);
                break;

            case Resources.SHEEP:
                p.getResources().setSheep(p.getResources().getSheep() + 1);
                break;

            case Resources.ORE:
                p.getResources().setOre(p.getResources().getOre() + 1);
                break;

            case Resources.BRICK:
                p.getResources().setBrick(p.getResources().getBrick() + 1);
                break;

            case Resources.WHEAT:
                p.getResources().setWheat(p.getResources().getWheat() + 1);
                break;
            default:
                server.Server.log.severe("Unrecognized resource :" + resource);
        }
   }
}
