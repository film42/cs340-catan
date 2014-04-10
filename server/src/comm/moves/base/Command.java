package comm.moves.base;

import static comm.moves.base.Command.moveFromJson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import comm.moves.AcceptTrade;
import comm.moves.BuildCity;
import comm.moves.BuildRoad;
import comm.moves.BuildSettlement;
import comm.moves.BuyDevCard;
import comm.moves.DiscardCards;
import comm.moves.FinishTurn;
import comm.moves.MaritimeTrade;
import comm.moves.Monopoly;
import comm.moves.Monument;
import comm.moves.RoadBuilding;
import comm.moves.RobPlayer;
import comm.moves.RollNumber;
import comm.moves.SendChat;
import comm.moves.Soldier;
import comm.moves.TradeOfferCommand;
import comm.moves.YearOfPlenty;

import model.InjectorFactory;
import model.messaging.LineImpl;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.messaging.Line;

import java.io.IOException;

/**
 * Created by: film42 on: 3/12/14.
 */
public abstract class Command implements Commandable {

	final static String SEND_CHAT = "/moves/sendChat";
	final static String ROLL_NUMBER = "/moves/rollNumber";
	final static String FINISH_TURN = "/moves/finishTurn";
	final static String BUY_DEV_CARD = "/moves/buyDevCard";
	final static String YEAR_OF_PLENTY = "/moves/Year_of_Plenty";
	final static String ROAD_BUILDING = "/moves/Road_Building";
	final static String SOLDIER = "/moves/Soldier";
	final static String MONOPOLY = "/moves/Monopoly";
	final static String MONUMENT = "/moves/Monument";
	final static String BUILD_ROAD = "/moves/buildRoad";
	final static String BUILD_SETTLEMENT = "/moves/buildSettlement";
	final static String BUILD_CITY = "/moves/buildCity";
	final static String OFFER_TRADE = "/moves/offerTrade";
	final static String ACCEPT_TRADE = "/moves/acceptTrade";
	final static String DISCARD_CARDS = "/moves/discardCards";
	final static String MARITIME_TRADE = "/moves/maritimeTrade";
	final static String ROB_PLAYER = "/moves/robPlayer";

	protected String type;

	protected int playerIndex;


    public String getType() {
        return type;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public abstract String getLogMessage();

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException{
        Player p = gameInfo.getData().getPlayerByIndex(getPlayerIndex());
        if(getLogMessage() != null){
            gameInfo.getData().getLog().addLine(new LineImpl(p.getName(), p.getName() + getLogMessage()));
        }
    }

    public static <T extends Command> T moveFromJson(String json, Class<T> type) throws JsonSyntaxException {
        Gson gson = new Gson();
        return type.cast(gson.fromJson(json, type));
    }

	// ////////////////////////////////// //
	// Switching on all possible commands //
	// ////////////////////////////////// //
	public static Command commandForType(String type, String json) {
		Command command = null;
		switch (type) {
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
            command = moveFromJson(json, TradeOfferCommand.class); break;
        case ACCEPT_TRADE:
            command = moveFromJson(json, AcceptTrade.class); break;
        case DISCARD_CARDS:
            command = moveFromJson(json, DiscardCards.class); break;
        case MARITIME_TRADE:
            command = moveFromJson(json, MaritimeTrade.class); break;
        case ROB_PLAYER:
            command = moveFromJson(json, RobPlayer.class); break;
        default:
            return null;
		}
		return command;
    }

}
