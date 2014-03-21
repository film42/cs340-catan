package comm.moves.tests;

import static comm.moves.base.Command.moveFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import com.google.inject.Injector;
import model.InjectorFactory;
import model.ModelModule;
import model.base.DeckImpl;
import model.base.GameImpl;
import model.base.GameInfoImpl;
import model.base.PlayerImpl;
import modelInterfaces.base.Deck;
import modelInterfaces.base.Game;
import modelInterfaces.base.GameInfo;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comm.moves.Monopoly;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;

public class MonopolyTest {

	// Constants
	private static final int HIGH_NUMBER = 99;
	private static final int LOW_NUMBER = 0;
	private static final int FIRST_PLAYER = 0;
	private static final int SECOND_PLAYER = 0;
	private static final int THIRD_PLAYER = 0;
	private static final int FOURTH_PLAYER = 0;

	private Game fakeGame;

	@Before
	public void setUp() throws Exception {

		// Set up generic list objects

		// Resource list with lots of resources
		Resources lotsOfResources = new model.base.ResourcesImpl();
		lotsOfResources.setBrick(HIGH_NUMBER);
		lotsOfResources.setOre(HIGH_NUMBER);
		lotsOfResources.setSheep(HIGH_NUMBER);
		lotsOfResources.setWheat(HIGH_NUMBER);
		lotsOfResources.setWood(HIGH_NUMBER);

		// DevCard list with lots of cards
		Deck lotsOfDevCards = new DeckImpl();
		lotsOfDevCards.setMonopoly(HIGH_NUMBER);
		lotsOfDevCards.setMonument(HIGH_NUMBER);
		lotsOfDevCards.setRoadBuilding(HIGH_NUMBER);
		lotsOfDevCards.setSoldier(HIGH_NUMBER);
		lotsOfDevCards.setYearOfPlenty(HIGH_NUMBER);

		// Resource List with nothing in it
		Resources noResources = new model.base.ResourcesImpl();
		noResources.setBrick(LOW_NUMBER);
		noResources.setOre(LOW_NUMBER);
		noResources.setSheep(LOW_NUMBER);
		noResources.setWheat(LOW_NUMBER);
		noResources.setWood(LOW_NUMBER);

		// Dev Cards list with nothing in it
		Deck noDevCards = new DeckImpl();
		noDevCards.setMonopoly(LOW_NUMBER);
		noDevCards.setMonument(LOW_NUMBER);
		noDevCards.setRoadBuilding(LOW_NUMBER);
		noDevCards.setSoldier(LOW_NUMBER);
		noDevCards.setYearOfPlenty(LOW_NUMBER);

        //Jon: I'm changing this to use dependancy injection.
        Injector injector = InjectorFactory.createInjector(new ModelModule());

		Player richMike = injector.getInstance(Player.class);
		richMike.setResources(lotsOfResources);
		richMike.setOldDevCards(lotsOfDevCards);
		richMike.setNewDevCards(lotsOfDevCards);
		richMike.setRoads(HIGH_NUMBER);
		richMike.setCities(HIGH_NUMBER);
		richMike.setSettlements(HIGH_NUMBER);
		richMike.setSoldiers(HIGH_NUMBER);
		richMike.setVictoryPoints(HIGH_NUMBER);
		richMike.setMonuments(HIGH_NUMBER);
		richMike.setLongestRoad(true);
		richMike.setLargestArmy(true);
		richMike.setPlayedDevCard(true);
		richMike.setDiscarded(true);
		richMike.setPlayerID(FIRST_PLAYER);
		richMike.setOrderNumber(FIRST_PLAYER);
		richMike.setName("Mike MoneyBags");
		richMike.setColor("Blue");

		Player richSteve = injector.getInstance(Player.class);
		richSteve.setResources(lotsOfResources);
		richSteve.setOldDevCards(lotsOfDevCards);
		richSteve.setNewDevCards(lotsOfDevCards);
		richSteve.setRoads(HIGH_NUMBER);
		richSteve.setCities(HIGH_NUMBER);
		richSteve.setSettlements(HIGH_NUMBER);
		richSteve.setSoldiers(HIGH_NUMBER);
		richSteve.setVictoryPoints(HIGH_NUMBER);
		richSteve.setMonuments(HIGH_NUMBER);
		richSteve.setLongestRoad(true);
		richSteve.setLargestArmy(true);
		richSteve.setPlayedDevCard(true);
		richSteve.setDiscarded(true);
		richSteve.setPlayerID(SECOND_PLAYER);
		richSteve.setOrderNumber(SECOND_PLAYER);
		richSteve.setName("Steve GreenBacks");
		richSteve.setColor("Green");

		Player poorPoorGuy = injector.getInstance(Player.class);
		poorPoorGuy.setResources(noResources);
		poorPoorGuy.setOldDevCards(noDevCards);
		poorPoorGuy.setNewDevCards(noDevCards);
		poorPoorGuy.setRoads(LOW_NUMBER);
		poorPoorGuy.setCities(LOW_NUMBER);
		poorPoorGuy.setSettlements(LOW_NUMBER);
		poorPoorGuy.setSoldiers(LOW_NUMBER);
		poorPoorGuy.setVictoryPoints(LOW_NUMBER);
		poorPoorGuy.setMonuments(LOW_NUMBER);
		poorPoorGuy.setLongestRoad(false);
		poorPoorGuy.setLargestArmy(false);
		poorPoorGuy.setPlayedDevCard(false);
		poorPoorGuy.setDiscarded(false);
		poorPoorGuy.setPlayerID(THIRD_PLAYER);
		poorPoorGuy.setOrderNumber(THIRD_PLAYER);
		poorPoorGuy.setName("PoorGuy");
		poorPoorGuy.setColor("Puce");

		Player userGuy = injector.getInstance(Player.class);
		userGuy.setResources(noResources);
		userGuy.setOldDevCards(noDevCards);
		userGuy.setNewDevCards(noDevCards);
		userGuy.setRoads(LOW_NUMBER);
		userGuy.setCities(LOW_NUMBER);
		userGuy.setSettlements(LOW_NUMBER);
		userGuy.setSoldiers(LOW_NUMBER);
		userGuy.setVictoryPoints(LOW_NUMBER);
		userGuy.setMonuments(LOW_NUMBER);
		userGuy.setLongestRoad(false);
		userGuy.setLargestArmy(false);
		userGuy.setPlayedDevCard(false);
		userGuy.setDiscarded(false);
		userGuy.setPlayerID(FOURTH_PLAYER);
		userGuy.setOrderNumber(FOURTH_PLAYER);
		userGuy.setName("Poor User");
		userGuy.setColor("Red");

		ArrayList<Player> fakePlayers = new ArrayList<Player>();
		fakePlayers.add(richMike);
		fakePlayers.add(richSteve);
		fakePlayers.add(userGuy);
		fakePlayers.add(poorPoorGuy);

		fakeGame = new GameImpl();
		fakeGame.setPlayers(fakePlayers);

	}

	@Test
	public void test_1() {
		assertEquals("OK", "OK");
		assertTrue(true);
		assertFalse(false);
	}

	@Test 
	public void testStealWood() {

		// Setup
		GameInfo fakeInfo = new GameInfoImpl(fakeGame);
		String json = "{\"resource\" : \"Wood\", \"playerIndex\": " + FOURTH_PLAYER + "}";

		// Create object
		Commandable monopoly = moveFromJson(json, Monopoly.class);

		// Execute command
		try {
			monopoly.execute(fakeInfo);
		} catch (IOException | InvalidCommandException e) {
			fail("Exception in testStealWood.execute();");
			return;
		}

		// test that it worked
		for (Player p : fakeInfo.getData().getPlayers()) {
			// test to make sure that those who had a wood lost one
			if (p.getOrderNumber() == FIRST_PLAYER || p.getOrderNumber() == SECOND_PLAYER) {
				assertEquals(p.getResources().getWood(), HIGH_NUMBER - 1);
			}
			// test to make sure that those who didn't have anything didn't lose anything
			else {
				assertEquals(p.getResources().getWood(), LOW_NUMBER);
			}
		}
	}

	@After
	public void tearDown() throws Exception {

	}
}
