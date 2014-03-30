package comm.moves.tests;

import comm.factory.FakeGameFactory;
import comm.moves.FinishTurn;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;
import model.base.GameInfoImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.TurnTracker;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static comm.moves.base.Command.moveFromJson;
import static comm.factory.FakeGameFactory.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FinishTurnTest {

	private Game fakeNormalGame;
	private GameInfo fakeInfo;

	@Before
	public void setUp() throws Exception {
		fakeNormalGame = FakeGameFactory.getNormalFakeGame();
		fakeInfo = new GameInfoImpl(fakeNormalGame);
	}

	@Test
	public void testIt() {
		// Setup
		String json = "{\"type\" : \"finishTurn\", \"playerIndex\": " + FIRST_PLAYER + "}";

		fakeInfo.getData().getTurnTracker().setStatus(TurnTracker.FIRST_ROUND);

		executeFinishTurnFor(FIRST_PLAYER);

		assertEquals(TurnTracker.FIRST_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(SECOND_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(SECOND_PLAYER);

		assertEquals(TurnTracker.FIRST_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(THIRD_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(THIRD_PLAYER);

		assertEquals(TurnTracker.FIRST_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(FOURTH_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(FOURTH_PLAYER);

		assertEquals(TurnTracker.SECOND_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(FOURTH_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(FOURTH_PLAYER);

		assertEquals(TurnTracker.SECOND_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(THIRD_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(THIRD_PLAYER);

		assertEquals(TurnTracker.SECOND_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(SECOND_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(SECOND_PLAYER);

		assertEquals(TurnTracker.SECOND_ROUND, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(FIRST_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		executeFinishTurnFor(FIRST_PLAYER);

		assertEquals(TurnTracker.ROLLING, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(FIRST_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		fakeInfo.getData().getTurnTracker().setStatus(TurnTracker.PLAYING);
		executeFinishTurnFor(FIRST_PLAYER);

		assertEquals(TurnTracker.ROLLING, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(SECOND_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		fakeInfo.getData().getTurnTracker().setStatus(TurnTracker.PLAYING);
		executeFinishTurnFor(SECOND_PLAYER);

		assertEquals(TurnTracker.ROLLING, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(THIRD_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		fakeInfo.getData().getTurnTracker().setStatus(TurnTracker.PLAYING);
		executeFinishTurnFor(THIRD_PLAYER);

		assertEquals(TurnTracker.ROLLING, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(FOURTH_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

		fakeInfo.getData().getTurnTracker().setStatus(TurnTracker.PLAYING);
		executeFinishTurnFor(FOURTH_PLAYER);

		assertEquals(TurnTracker.ROLLING, fakeInfo.getData().getTurnTracker().getStatus());
		assertEquals(FIRST_PLAYER, fakeInfo.getData().getTurnTracker().getCurrentTurn());

	}

	private void executeFinishTurnFor(int playerOrder) {
		// Player finishes turn
		String json = "{\"type\" : \"finishTurn\", \"playerIndex\": " + playerOrder + "}";

		// Create object
		Commandable finishTurn = moveFromJson(json, FinishTurn.class);

		try {
			finishTurn.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			fail("Exception in .execute();");
			return;
		}
	}
}
