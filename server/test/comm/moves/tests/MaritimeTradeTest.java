package comm.moves.tests;

import static comm.moves.base.Command.moveFromJson;
import static comm.factory.FakeGameFactory.*;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.io.IOException;

import model.base.GameInfoImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;

import org.junit.Before;
import org.junit.Test;

import comm.factory.FakeGameFactory;
import comm.moves.MaritimeTrade;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;

public class MaritimeTradeTest {
	private Game fakeNormalGame;
	private GameInfo fakeInfo;

	@Before
	public void setUp() throws Exception {
		fakeNormalGame = FakeGameFactory.getNormalFakeGame();
		fakeInfo = new GameInfoImpl(fakeNormalGame);
	}

	@Test
	public void testValidWoodForSheep() {

		assertTrue(performExecute(FIRST_PLAYER, 4, "Wood", "Sheep"));

		assertEquals(HIGH_NUMBER - 4, fakeInfo.getData().getPlayerByIndex(FIRST_PLAYER).getResources().getWood());
		assertEquals(HIGH_NUMBER + 1, fakeInfo.getData().getPlayerByIndex(FIRST_PLAYER).getResources().getSheep());
	}

	@Test
	public void testInvalidWoodForSheep() {

		// this player does not have the needed resources
		assertFalse(performExecute(THIRD_PLAYER, 4, "Wood", "Sheep"));
	}

	@Test
	public void testEmptyBank() {

		fakeInfo.getData().getBank().setBrick(0);
		
		assertFalse(performExecute(FIRST_PLAYER, 4, "Wood", "Brick"));
		
	}

	/**
	 * @param playerID
	 * @param ratio
	 * @param inputResource
	 * @param outputResource
	 * @return whether it was successful or not
	 */
	private boolean performExecute(int playerID, int ratio, String inputResource, String outputResource){

		String json = "{\"type\" : \"maritimeTrade\", \"playerIndex\": " + playerID + ", \"ratio\": " + ratio + ", \"inputResource\": \""
				+ inputResource + "\", \"outputResource\": \"" + outputResource + "\"}";

		// Create object
		Commandable maritimeTrade = moveFromJson(json, MaritimeTrade.class);

		try {
			maritimeTrade.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			return false;
		}
		return true;
	}

}
