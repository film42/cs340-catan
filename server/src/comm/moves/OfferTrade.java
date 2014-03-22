package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import comm.moves.form.CardDeck;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Resources;
import modelInterfaces.base.Player;

import java.io.IOException;

/**
 * Created by: film42 on: 3/13/14.
 */
public class OfferTrade extends Command {

    private CardDeck offer;
    private int receiver;

    public CardDeck getOffer() {
        return offer;
    }

    public int getReceiver() {
        return receiver;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {

        Game game = gameInfo.getData();
        Player playerOffer = game.getPlayerByIndex(playerIndex);
        Player playerReceiver = game.getPlayerByIndex(receiver);

        //check if trade is will to accept
        if ( !playerOffer.isWillAcceptTrade())
            return;

        Resources resourcesOffer = playerOffer.getResources();
        Resources resourcesReceiver = playerReceiver.getResources();

        //resource offered
        if(offer.getBrick() > 0){
            resourcesOffer.setBrick(resourcesOffer.getBrick() - offer.getBrick());
            resourcesReceiver.setBrick(resourcesReceiver.getBrick() + offer.getBrick());
        }
        //resource asked for
        else if (offer.getBrick() < 0){
            resourcesOffer.setBrick(resourcesOffer.getBrick() + Math.abs(offer.getBrick()));
            resourcesReceiver.setBrick(resourcesReceiver.getBrick() - Math.abs(offer.getBrick()));
        }

        //resource offered
        if(offer.getOre()> 0){
            resourcesOffer.setOre(resourcesOffer.getOre() - offer.getOre());
            resourcesReceiver.setOre(resourcesReceiver.getOre() + offer.getOre());
        }
        //resource asked for
        else if (offer.getOre() < 0){
            resourcesOffer.setOre(resourcesOffer.getOre() + Math.abs(offer.getOre()));
            resourcesReceiver.setOre(resourcesReceiver.getOre() - Math.abs(offer.getOre()));
        }

        //resource offered
        if(offer.getWood() > 0){
            resourcesOffer.setWood(resourcesOffer.getWood() - offer.getWood());
            resourcesReceiver.setWood(resourcesReceiver.getWood() + offer.getWood());
        }
        //resource asked for
        else if (offer.getWood() < 0){
            resourcesOffer.setWood(resourcesOffer.getWood() + Math.abs(offer.getWood()));
            resourcesReceiver.setWood(resourcesReceiver.getWood() - Math.abs(offer.getWood()));
        }

        //resource offered
        if(offer.getWheat() > 0){
            resourcesOffer.setWheat(resourcesOffer.getWheat() - offer.getWheat());
            resourcesReceiver.setWheat(resourcesReceiver.getWheat() + offer.getWheat());
        }
        //resource asked for
        else if (offer.getWheat() < 0){
            resourcesOffer.setWheat(resourcesOffer.getWheat() + Math.abs(offer.getWheat()));
            resourcesReceiver.setWheat(resourcesReceiver.getWheat() - Math.abs(offer.getWheat()));
        }

        //resource offered
        if(offer.getSheep() > 0){
            resourcesOffer.setSheep(resourcesOffer.getSheep() - offer.getSheep());
            resourcesReceiver.setSheep(resourcesReceiver.getSheep() + offer.getSheep());
        }
        //resource asked for
        else if (offer.getSheep() < 0){
            resourcesOffer.setSheep(resourcesOffer.getSheep() + Math.abs(offer.getSheep()));
            resourcesReceiver.setSheep(resourcesReceiver.getSheep() - Math.abs(offer.getSheep()));
        }
        gameInfo.setData(game);
    }
}
