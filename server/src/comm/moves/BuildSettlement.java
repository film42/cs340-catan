package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.VertexLocation;
import model.map.MapImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class BuildSettlement extends Command {

    private boolean free;
    private VertexLocation vertexLocation;

    public boolean isFree() {
        return free;
    }

    public VertexLocation getVertexLocation() {
        return vertexLocation;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
        Game game = gameInfo.getData();
        Player player = game.getPlayerByIndex(playerIndex);

        // Check for errors!
        if(player == null) throw new InvalidCommandException("Bad user ID!");

        // Get Resource list
        Resources r = player.getResources();

        // Is the settlement free?
        if(free) {

            // Build a free settlement
            buildSettlement(game);

        }
        // Are there enough resources?
        // NOT REQUIRED: But a good idea
        else if(r.getBrick() > 0 && r.getWheat() > 0
                && r.getSheep() > 0 & r.getWood() > 0) {

            // Decrement the resources
            r.setBrick(r.getBrick() - 1);
            r.setSheep(r.getSheep() - 1);
            r.setWheat(r.getWheat() - 1);
            r.setWood(r.getWood() - 1);

            // Add Settlement ot map
            buildSettlement(game);
        } else {
            throw new InvalidCommandException("Not enough resources!");
        }

        gameInfo.setData(game);
    }

    private Game buildSettlement(Game game) {
        MapImpl map = (MapImpl)game.getMap();
        map.addSettlement(playerIndex, vertexLocation);
        game.setMap(map);

        return game;
    }

}
