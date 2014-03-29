package comm.moves.tests;

import comm.moves.DiscardCards;
import comm.moves.base.Commandable;
import model.base.GameInfoImpl;
import modelInterfaces.base.*;
import org.junit.Before;
import org.junit.Test;

import static comm.moves.base.Command.moveFromJson;
import static comm.moves.tests.FakeGameFactory.FOURTH_PLAYER;
import static comm.moves.tests.FakeGameFactory.HIGH_NUMBER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by: film42 on: 3/28/14.
 */
public class DiscardCardsTest {
    private Game fakeWealthyGame;
    private Game fakeNormalGame;

    @Before
    public void setUp() throws Exception {
        fakeWealthyGame = FakeGameFactory.getWealthyFakeGame();
        fakeNormalGame = FakeGameFactory.getNormalFakeGame();
    }

    @Test
    public void testRemoveNothing() {
        // Setup
        GameInfo fakeInfo = new GameInfoImpl(fakeWealthyGame);
        Game game = fakeInfo.getData();
        game.getTurnTracker().setStatus(TurnTracker.DISCARDING);
        game.getTurnTracker().setCurrentTurn(FOURTH_PLAYER);
        Player user = game.getPlayerByIndex(FOURTH_PLAYER);
        user.setDiscarded(false);

        String json = "{ type: \"discardCards\", playerIndex: " + FOURTH_PLAYER + ", discardedCards: { " +
                "brick: 0, ore: 0, sheep: 0, wheat: 0, wood: 0 } }";

        // Create object
        Commandable comm = moveFromJson(json, DiscardCards.class);

        // Execute command
        try {
            comm.execute(fakeInfo);
        } catch (Exception e) {
            fail("Exception in .execute(): " + e.getMessage());
            return;
        }

        Resources resources = user.getResources();

        // Test to make sure they were charged
        assertEquals(HIGH_NUMBER, resources.getBrick());
        assertEquals(HIGH_NUMBER, resources.getOre());
        assertEquals(HIGH_NUMBER, resources.getSheep());
        assertEquals(HIGH_NUMBER, resources.getWheat());
        assertEquals(HIGH_NUMBER, resources.getWood());
    }

    @Test
    public void testRemoveOneOfEach() {
        // Setup
        GameInfo fakeInfo = new GameInfoImpl(fakeWealthyGame);
        Game game = fakeInfo.getData();
        game.getTurnTracker().setStatus(TurnTracker.DISCARDING);
        game.getTurnTracker().setCurrentTurn(FOURTH_PLAYER);
        Player user = game.getPlayerByIndex(FOURTH_PLAYER);
        user.setDiscarded(false);


        String json = "{ type: \"discardCards\", playerIndex: " + FOURTH_PLAYER + ", discardedCards: { " +
                "brick: 1, ore: 1, sheep: 1, wheat: 1, wood: 1 } }";

        // Create object
        Commandable comm = moveFromJson(json, DiscardCards.class);

        // Execute command
        try {
            comm.execute(fakeInfo);
        } catch (Exception e) {
            fail("Exception in .execute(): " + e.getMessage());
            return;
        }

        Resources resources = user.getResources();

        // Test to make sure they were charged
        assertEquals(HIGH_NUMBER - 1, resources.getBrick());
        assertEquals(HIGH_NUMBER - 1, resources.getOre());
        assertEquals(HIGH_NUMBER - 1, resources.getSheep());
        assertEquals(HIGH_NUMBER - 1, resources.getWheat());
        assertEquals(HIGH_NUMBER - 1, resources.getWood());
    }

}
