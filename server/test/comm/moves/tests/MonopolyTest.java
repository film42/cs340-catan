package comm.moves.tests;

import static comm.moves.base.Command.moveFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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

public class MonopolyTest {

	// Constants
	private static final int HIGH_NUMBER = 99;
	private static final int LOW_NUMBER = 0;

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

		// Create a rich player
		Player richMike = new PlayerImpl();
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
		richMike.setPlayerID(HIGH_NUMBER);
		richMike.setOrderNumber(HIGH_NUMBER);
		richMike.setName("Mike MoneyBags");
		richMike.setColor("Blue");

		Player richSteve = new PlayerImpl();
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
		richSteve.setPlayerID(HIGH_NUMBER);
		richSteve.setOrderNumber(HIGH_NUMBER);
		richSteve.setName("Steve GreenBacks");
		richSteve.setColor("Green");

		Player poorPoorGuy = new PlayerImpl();
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
		poorPoorGuy.setPlayerID(LOW_NUMBER);
		poorPoorGuy.setOrderNumber(LOW_NUMBER);
		poorPoorGuy.setName("PoorGuy");
		poorPoorGuy.setColor("Puce");

		ArrayList<Player> fakePlayers = new ArrayList<Player>();
		Game fakeGame = new GameImpl();
		fakeGame.setPlayers(fakePlayers);
		GameInfo fakeInfo = new GameInfoImpl(fakeGame);
		String json = "\"resource\" : \"Brick\", \"playerIndex\": 0";
		Monopoly monopoly = moveFromJson(json, Monopoly.class);
	}

	@Test
	public void test_1() {
		assertEquals("OK", "OK");
		assertTrue(true);
		assertFalse(false);
	}

	@Test 
	public void testStealWood() { 
		
	}

	@After
	public void tearDown() throws Exception {

	}
}
