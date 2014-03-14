package model.facade;

import com.google.gson.JsonSyntaxException;
import comm.moves.*;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;
import model.Model;
import static comm.moves.base.Command.moveFromJson;
import java.io.IOException;

/**
 * Created by qzcx on 3/7/14.
 */
public class MoveFacade {

    final String SEND_CHAT = "/moves/sendChat";
    final String ROLL_NUMBER = "/moves/rollNumber";
    final String FINISH_TURN = "/moves/finishTurn";
    final String BUY_DEV_CARD = "/moves/buyDevCard";
    final String YEAR_OF_PLENTY = "/moves/Year_of_Plenty";
    final String ROAD_BUILDING = "/moves/Road_Building";
    final String SOLDIER = "/moves/Soldier";
    final String MONOPOLY = "/moves/Monopoly";
    final String MONUMENT = "/moves/Monument";
    final String BUILD_ROAD = "/moves/buildRoad";
    final String BUILD_SETTLEMENT = "/moves/buildSettlement";
    final String BUILD_CITY = "/moves/buildCity";
    final String OFFER_TRADE = "/moves/offerTrade";
    final String ACCEPT_TRADE = "/moves/acceptTrade";
    final String DISCARD_CARDS = "/moves/discardCards";

    private Model m_model;

    public MoveFacade(Model model) {
        m_model = model;
    }

    public boolean onMove(String json, String url) {
        //TODO decide whether we want one method per move in this function
        //TODO implement this method to use model
        Commandable command = null;
        try {
            // ////////////////////////////////// //
            // Switching on all possible commands //
            // ////////////////////////////////// //
            switch (url) {
                case SEND_CHAT:
                    command = moveFromJson(json, SendChat.class); break;
                case ROLL_NUMBER:
                    command = moveFromJson(json, RollNumber.class); break;
                case FINISH_TURN:
                    command = moveFromJson(json, FinishTurn.class); break;
                case BUY_DEV_CARD:
                    command = moveFromJson(json, BuyDevCard.class); break;
                case YEAR_OF_PLENTY:
                    command = moveFromJson(json, YearOfPlenty.class); break;
                case ROAD_BUILDING:
                    command = moveFromJson(json, RoadBuilding.class); break;
                case SOLDIER:
                    command = moveFromJson(json, Soldier.class); break;
                case MONOPOLY:
                    command = moveFromJson(json, Monopoly.class); break;
                case MONUMENT:
                    command = moveFromJson(json, Monument.class); break;
                case BUILD_ROAD:
                    command = moveFromJson(json, BuildRoad.class); break;
                case BUILD_SETTLEMENT:
                    command = moveFromJson(json, BuildSettlement.class); break;
                case BUILD_CITY:
                    command = moveFromJson(json, BuildCity.class); break;
                case OFFER_TRADE:
                    command = moveFromJson(json, OfferTrade.class); break;
                case ACCEPT_TRADE:
                    command = moveFromJson(json, AcceptTrade.class); break;
                case DISCARD_CARDS:
                    command = moveFromJson(json, DiscardCards.class); break;
                default:
                    return false;
            }

            // Run this command then return OK
            command.execute();
            return true;


        // ERRORS
        } catch (IOException e) {
            // Server Error: Our fault
            return false;
        } catch (InvalidCommandException e) {
            // Syntax Correct, but Error
            return false;
        } catch (JsonSyntaxException e) {
            // Caused by bad json.. could not serialize
            return false;
        }
    }

}
