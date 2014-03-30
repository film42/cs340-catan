package comm.moves.tests;

import static comm.factory.FakeGameFactory.*;
import static comm.moves.base.Command.moveFromJson;
import static org.junit.Assert.*;

import java.io.IOException;

import model.base.GameInfoImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.TurnTracker;

import org.junit.Before;
import org.junit.Test;

import comm.factory.FakeGameFactory;
import comm.moves.RobPlayer;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;

public class RobPlayerTest {


	private Game fakeNormalGame;

	private GameInfo fakeInfo;

	@Before
	public void setUp() throws Exception {
		fakeNormalGame = FakeGameFactory.getNormalFakeGame();

		fakeInfo = new GameInfoImpl(fakeNormalGame);
	}

	@Test
	public void testRobSomeone() {

		if (!performExecute(FOURTH_PLAYER, FIRST_PLAYER, 2, 2))
			fail("Exception in .execute()!");

		assertEquals(LOW_NUMBER + 1, fakeInfo.getData().getPlayerByIndex(FOURTH_PLAYER).getResources().getResourceCount());
		assertEquals((HIGH_NUMBER * 5) - 1, fakeInfo.getData().getPlayerByIndex(FIRST_PLAYER).getResources().getResourceCount());

	}

	private boolean performExecute(int playerIndex, int victimIndex, int x, int y) {
		// Setup

		String json = "{\"type\": \"robPlayer\",\"playerIndex\": " + playerIndex + ",\"victimIndex\": " + victimIndex + ",\"location\": {\"x\": \"" + x
 + "\",\"y\": \"" + y + "\"}}";
		System.out.println(json);
		// Create object
		Commandable robPlayer = moveFromJson(json, RobPlayer.class);

		fakeInfo.getData().getTurnTracker().setStatus(TurnTracker.ROBBING);

		// Execute command
		try {
			robPlayer.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			return false;
		}

		return true;
	}

}



