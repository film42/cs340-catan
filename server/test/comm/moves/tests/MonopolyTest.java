package comm.moves.tests;

import static comm.moves.base.Command.moveFromJson;
import static comm.factory.FakeGameFactory.FIRST_PLAYER;
import static comm.factory.FakeGameFactory.FOURTH_PLAYER;
import static comm.factory.FakeGameFactory.HIGH_NUMBER;
import static comm.factory.FakeGameFactory.LOW_NUMBER;
import static comm.factory.FakeGameFactory.SECOND_PLAYER;
import static comm.factory.FakeGameFactory.THIRD_PLAYER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import comm.factory.FakeGameFactory;
import model.base.GameInfoImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comm.moves.Monopoly;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;

/**
 * This has partitioning in it. I test that each resource works when there are two others with the specified resource and one other that does not. I
 * also test the case where nobody has the specified resource and also where everyone has the specified resource.
 * 
 * @author Steve Allred
 * 
 */
public class MonopolyTest {

	private Game fakeNormalGame;
	private Game fakeWealthyGame;
	private Game fakePoorGame;

	@Before
	public void setUp() throws Exception {
		fakeNormalGame = FakeGameFactory.getNormalFakeGame();
		fakeWealthyGame = FakeGameFactory.getWealthyFakeGame();
		fakePoorGame = FakeGameFactory.getPoorFakeGame();
	}

	@Test
	public void testStealWood() {

		// Setup
		GameInfo fakeInfo = new GameInfoImpl(fakeNormalGame);
		String json = "{\"resource\" : \"Wood\", \"playerIndex\": " + FOURTH_PLAYER + "}";

		// Create object
		Commandable monopoly = moveFromJson(json, Monopoly.class);

		// Execute command
		try {
			monopoly.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			fail("Exception in .execute();");
			return;
		}

		// test that it worked
		for (Player p : fakeInfo.getData().getPlayers()) {
			// test to make sure that those who had a wood lost one
			if (!(p.getOrderNumber() == FOURTH_PLAYER)) {
				assertEquals(LOW_NUMBER, p.getResources().getWood());
			}
			// test to make sure that the user who played the card got the resources from the two that did
			else
				assertEquals(HIGH_NUMBER * 2, p.getResources().getWood());
		}
	}


	@Test
	public void testStealWoodButNobodyHasWood() {

		// Setup
		GameInfo fakeInfo = new GameInfoImpl(fakePoorGame);
		String json = "{\"resource\" : \"Wood\", \"playerIndex\": " + FOURTH_PLAYER + "}";

		// Create object
		Commandable monopoly = moveFromJson(json, Monopoly.class);

		// Execute command
		try {
			monopoly.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			fail("Exception in .execute();");
			return;
		}

		// test that it worked
		for (Player p : fakeInfo.getData().getPlayers()) {
			// Nobody had wood, so everybody should remain at zero
			assertEquals(LOW_NUMBER, p.getResources().getWood());
		}
	}

	@Test
	public void testStealBrickAndEverybodyHasBrick() {

		// Setup
		GameInfo fakeInfo = new GameInfoImpl(fakeWealthyGame);
		String json = "{\"resource\" : \"Brick\", \"playerIndex\": " + FOURTH_PLAYER + "}";

		// Create object
		Commandable monopoly = moveFromJson(json, Monopoly.class);

		// Execute command
		try {
			monopoly.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			fail("Exception in testStealBrick.execute();");
			return;
		}

		// test that it worked
		for (Player p : fakeInfo.getData().getPlayers()) {
			// test to make sure that those who had a Brick lost one
			if (!(p.getOrderNumber() == FOURTH_PLAYER)) {
				assertEquals(LOW_NUMBER, p.getResources().getBrick());
			}
			// The user should have received three resources
			else {
				assertEquals(HIGH_NUMBER * 4, p.getResources().getBrick());
			}
		}
	}

	@After
	public void tearDown() throws Exception {

	}
}
