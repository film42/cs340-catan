package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.Location;
import modelInterfaces.base.*;
import modelInterfaces.map.Robber;


import java.io.IOException;
import java.util.*;

/**
 * Created by: film42 on: 3/13/14.
 */
public class Soldier extends Command {

    private int victimIndex;
    private Location location;

    public int getVictimIndex() {
        return victimIndex;
    }

    public Location getRobberSpot() {
        return location;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        // Move robber to robber spot
        Game game = gameInfo.getData();
        Robber robber = game.getMap().getRobber();
        robber.setX(getRobberSpot().getX());
        robber.setY(getRobberSpot().getY());

        Player currentPlayer = game.getPlayerByIndex(getPlayerIndex());

        if(victimIndex != Player.NO_PLAYER) {
            // Steal one random resource from victim.
            // (make sure they have at least one)
            // TODO: Logic check this for soldier
            Player victim = game.getPlayerByIndex(getVictimIndex());
            stealResource(victim.getResources(), currentPlayer.getResources());
        }

        currentPlayer.setSoldiers(currentPlayer.getSoldiers() + 1);

        // Reduce soldier card count
        Deck cards = currentPlayer.getOldDevCards();
        cards.setSoldier(cards.getSoldier()-1);

        // Prevent additional dev card playing
        currentPlayer.setPlayedDevCard(true);

        // Determine largest army
        game.determineLargestArmy();
    }

    private void stealResource(Resources stealFrom, Resources giveTo) throws InvalidCommandException {

        List<String> availableList = stealFrom.getAvailibleResources();

        if(availableList.size() <= 0){
            throw new InvalidCommandException("Steal Resource called on player with no resources");
        }

        int index = (int)(Math.random()*availableList.size());
        String type = availableList.get(index);
        stealFrom.decrementResourceByString(type, 1);
        giveTo.incrementResourceByString(type, 1);
    }
}
