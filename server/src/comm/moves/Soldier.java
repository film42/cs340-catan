package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.Location;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import modelInterfaces.map.Robber;


import java.io.IOException;
import java.util.*;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Soldier extends Command {

    private int victimIndex;
    private Location robberSpot;

    public int getVictimIndex() {
        return victimIndex;
    }

    public Location getRobberSpot() {
        return robberSpot;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        //move robber to robberspot
        Game game = gameInfo.getData();
        Robber robber = game.getMap().getRobber();
        robber.setX(robberSpot.getX());
        robber.setY(robberSpot.getY());

        //steal one random resource from victim. (make sure they have at least one);
        Player player = game.getPlayerByIndex(victimIndex);
        Resources resources = player.getResources();

        if (resources.getResourceCount() <= 0)
            return;

        Random random = new Random();
        int randomInt = random.nextInt(4);
        switch (randomInt) {
            case 0:
                if (resources.getBrick() > 0) {
                    resources.setBrick(resources.getBrick() - 1);
                }
                else{
                    stealOneWithResource(resources);
                }
                break;
            case 1:
                if (resources.getOre() > 0) {
                    resources.setOre(resources.getOre() - 1);
                }
                else{
                    stealOneWithResource(resources);
                }
                break;
            case 2:
                if (resources.getSheep() > 0) {
                    resources.setSheep(resources.getSheep() - 1);
                }
                else{
                    stealOneWithResource(resources);
                }
                break;
            case 3:
                if (resources.getWheat() > 0) {
                    resources.setWheat(resources.getWheat() - 1);
                }
                else{
                    stealOneWithResource(resources);
                }
            case 4:
                if (resources.getWood() > 0) {
                    resources.setWood(resources.getWood() - 1);
                }
                else{
                    stealOneWithResource(resources);
                }
                break;
            default:
                stealOneWithResource(resources);
                break;
        }

        gameInfo.setData(game);

    }

    private void stealOneWithResource(Resources resources) {
        if (resources.getBrick() > 0) {
            resources.setBrick(resources.getBrick() - 1);
           return;
        }

        if (resources.getOre() > 0) {
            resources.setOre(resources.getOre() - 1);
            return;
        }

        if (resources.getSheep() > 0) {
            resources.setSheep(resources.getSheep() - 1);
            return;
        }

        if (resources.getWheat() > 0) {
            resources.setWheat(resources.getWheat() - 1);
            return;

        }

        if (resources.getWood() > 0) {
            resources.setWood(resources.getWood() - 1);
            return;
        }

    }
}
