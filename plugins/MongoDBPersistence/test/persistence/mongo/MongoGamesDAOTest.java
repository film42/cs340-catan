package persistence.mongo;

import persistence.GameDTO;
import persistence.GamesDAO;
import persistence.PersistenceProvider;
import persistence.UsersDAO;

import static org.junit.Assert.*;

/**
 * Created by: film42 on: 4/14/14.
 */
public class MongoGamesDAOTest {

    private PersistenceProvider provider = new MongoPersistenceProvider();

    private GamesDAO gamesDAO;
    private UsersDAO usersDAO;

    private GameDTO gameDTO1;
    private GameDTO gameDTO2;

    @org.junit.Before
    public void setUp() throws Exception {

        // Connect to test DB
        Connection conn = new Connection("","","localhost","catan_test",27017);
        MongoPersistenceProvider.getConnection(conn);

        gamesDAO = provider.getGamesDAO();
        usersDAO = provider.getUsersDAO();

        gameDTO1 = new GameDTO(1, "Game1", "initialPoint1", "checkPoint1", "commandList1", 10);
        gameDTO2 = new GameDTO(2, "Game2", "initialPoint2", "checkPoint2", "commandList2", 20);

    }

    @org.junit.After
    public void tearDown() throws Exception {
        provider.wipeDatabase();
    }

    //
    // Core
    //

    @org.junit.Test
    public void testGetGameIds() throws Exception {
        //add game
        gamesDAO.addGame(gameDTO1.getId(),gameDTO1.getName(), gameDTO1.getInitialPoint());
        //add another game
        gamesDAO.addGame(gameDTO2.getId(),gameDTO2.getName(), gameDTO2.getInitialPoint());
        if (gamesDAO.getGameIds().size() != 2)
            fail();
    }

    @org.junit.Test
    public void testAddGame() throws Exception {
        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getId(), gameDTO1.getName(), gameDTO1.getInitialPoint());

        //check
        assertEquals(gamesDAO.getName(gameId), gameDTO1.getName());
        assertEquals(gamesDAO.getInitialModel(gameId), gameDTO1.getInitialPoint());
    }

    @org.junit.Test
    public void testCommandList() throws Exception {
        String newUpdateList = "command1";

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getId(),gameDTO1.getName(), gameDTO1.getInitialPoint());

        assertTrue(gamesDAO.updateCommandList(gameId, newUpdateList));

        // validate checkPoint
        assertEquals(gamesDAO.getCommandList(gameId), newUpdateList);

    }

    @org.junit.Test
    public void testUpdateCheckpoint() throws Exception {
        String newCheckPoint = "CheckPoint 5";
        int newCursor = 4;

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getId(),gameDTO1.getName(), gameDTO1.getInitialPoint());

        assertTrue(gamesDAO.updateCheckpoint(gameId, newCheckPoint, newCursor));

        // validate checkPoint
        assertEquals(gamesDAO.getCheckpoint(gameId), newCheckPoint);

        //validate cursor
        if (gamesDAO.getCursor(gameId) != newCursor) {
            fail();
        }
    }

    @org.junit.Test
    public void testClearDatabase() throws Exception {

        gamesDAO.clearDatabase();

        // Ensure no games
        assertEquals(0, gamesDAO.getGameIds().size());

    }
}
