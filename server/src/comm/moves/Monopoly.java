package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;

import java.io.IOException;

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

		// Get the game model
		Game game = gameInfo.getData();

		// Cycle through all players, steal a card, increase count, and skip if it's the current player
		int cardsStolen = 0;
		for (Player p : game.getPlayers()) {
			if (p.getOrderNumber() != this.playerIndex) {
				switch (resource) {
				case Resources.WOOD:
					if (p.getResources().getWood() > 0) {
						p.getResources().setWood(p.getResources().getWood() - 1);
						cardsStolen++;
					}
					break;
				case Resources.SHEEP:
					if (p.getResources().getSheep() > 0) {
						p.getResources().setSheep(p.getResources().getSheep() - 1);
						cardsStolen++;
					}
					break;
				case Resources.ORE:
					if (p.getResources().getOre() > 0) {
						p.getResources().setOre(p.getResources().getOre() - 1);
						cardsStolen++;
					}
					break;
				case Resources.BRICK:
					if (p.getResources().getBrick() > 0) {
						p.getResources().setBrick(p.getResources().getBrick() - 1);
						cardsStolen++;
					}
					break;
				case Resources.WHEAT:
					if (p.getResources().getWheat() > 0) {
						p.getResources().setWheat(p.getResources().getWheat() - 1);
						cardsStolen++;
					}
					break;
				default:
					server.Server.log.severe("Unrecognized resource selected: " + resource);
				}
			}
		}

		// give the qty of stolen cards to the user
		int oldQty = 0;
		switch (resource) {
            case Resources.WOOD:
                oldQty = game.getPlayerByIndex(playerIndex).getResources().getWood();
                game.getPlayerByIndex(playerIndex).getResources().setWood(oldQty + cardsStolen);
                break;
            case Resources.SHEEP:
                oldQty = game.getPlayerByIndex(playerIndex).getResources().getSheep();
                game.getPlayerByIndex(playerIndex).getResources().setSheep(oldQty + cardsStolen);
                break;
            case Resources.ORE:
                oldQty = game.getPlayerByIndex(playerIndex).getResources().getOre();
                game.getPlayerByIndex(playerIndex).getResources().setOre(oldQty + cardsStolen);
                break;
            case Resources.BRICK:
                oldQty = game.getPlayerByIndex(playerIndex).getResources().getBrick();
                game.getPlayerByIndex(playerIndex).getResources().setBrick(oldQty + cardsStolen);
                break;
            case Resources.WHEAT:
                oldQty = game.getPlayerByIndex(playerIndex).getResources().getWheat();
                game.getPlayerByIndex(playerIndex).getResources().setWheat(oldQty + cardsStolen);
                break;
            default:
                server.Server.log.severe("Unrecognized resource selected :" + resource);
		}

        // Write our changes
        gameInfo.setData(game);
	}
}
