package Test;

import copied.PersistenceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistance.SQLPersistenceProvider;
import persistance.GameDTO;
import persistance.SQLGamesDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GamesDAOTest {

    private SQLGamesDAO gamesDAO;

    private GameDTO gameDTO1;
    private GameDTO gameDTO2;
    PersistenceProvider sqlProvider;

    @Before
    public void setUp() throws Exception {

        sqlProvider = new SQLPersistenceProvider();

        gamesDAO = new SQLGamesDAO();
        gameDTO1 = new GameDTO(0, "Game1", "initialPoint1", "checkPoint1", "commandList1", 10);
        gameDTO2 = new GameDTO(0, "Game2", "initialPoint2", "checkPoint2", "commandList2", 20);

    }

    @Test
    public void test_addGame() {
        // add game and get initial Model
// add game and get initial Model

        sqlProvider.beginTransaction();
        //delete first
        gamesDAO.deleteGames();

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getName(), gameDTO1.getInitialPoint());

        //check
        assertEquals(gamesDAO.getName(gameId),gameDTO1.getName());
        assertEquals(gamesDAO.getInitialModel(gameId), gameDTO1.getInitialPoint());
        sqlProvider.commitTransaction();;

    }

    @Test
    public void test_updateCheckPoint() {
        // uadate and get checkkpoint

        String newCheckPoint = "CheckPoint 5";
        int newCursor = 4;

        sqlProvider.beginTransaction();

        //delete first
        gamesDAO.deleteGames();

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getName(), gameDTO1.getInitialPoint());

        assertTrue(gamesDAO.updateCheckpoint(gameId, newCheckPoint, newCursor));

        // validate checkPoit
        assertEquals(gamesDAO.getCheckpoint(gameId), newCheckPoint);

        //validate cursor
        if (gamesDAO.getCursor(gameId) != newCursor){
            fail();
        }
        sqlProvider.commitTransaction();
    }

    @Test
    public void test_commandList() {

        String newUpdateList = "command1";

        sqlProvider.beginTransaction();

        //delete first
        gamesDAO.deleteGames();

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getName(), gameDTO1.getInitialPoint());

        assertTrue(gamesDAO.updateCommandList(gameId, newUpdateList));

        // validate checkPoit
        assertEquals(gamesDAO.getCommandList(gameId), newUpdateList);

        sqlProvider.commitTransaction();
    }

    @Test
    public void test_getGameIds() {

        sqlProvider.beginTransaction();

        //delete first
        gamesDAO.deleteGames();

        //add game
        gamesDAO.addGame(gameDTO1.getName(), gameDTO1.getInitialPoint());
        //add another game
        gamesDAO.addGame(gameDTO2.getName(), gameDTO2.getInitialPoint());
        if (gamesDAO.getGameIds().size() != 2)
            fail();
        sqlProvider.commitTransaction();
    }

    @Test
    public void test_getGame() {


        sqlProvider.beginTransaction();
        //delete first
        gamesDAO.deleteGames();

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getName(), gameDTO1.getInitialPoint());
        gamesDAO.updateCheckpoint(gameId, gameDTO1.getCheckPoint(), gameDTO1.getCheckPointCursor());
        gamesDAO.updateCommandList(gameId, gameDTO1.getCommandList());

        //add another game
        int gameId2 = gamesDAO.addGame(gameDTO2.getName(), gameDTO2.getInitialPoint());
        gamesDAO.updateCheckpoint(gameId2, gameDTO2.getCheckPoint(), gameDTO2.getCheckPointCursor());
        gamesDAO.updateCommandList(gameId2, gameDTO2.getCommandList());

        List<GameDTO> games = gamesDAO.getGames();
        sqlProvider.commitTransaction();

        // validate
        if (games.size() != 2) {
            fail();
        } else {
            assertTrue(compareGame(games.get(0), gameDTO1));
            assertTrue(compareGame(games.get(1), gameDTO2));
        }
    }

    @Test
    public void test_deleteGame() {

         sqlProvider.beginTransaction();
        //delete first
        gamesDAO.deleteGames();

        //add game
        int gameId = gamesDAO.addGame(gameDTO1.getName(), gameDTO1.getInitialPoint());

        //add another game
        int gameId2 = gamesDAO.addGame(gameDTO2.getName(), gameDTO2.getInitialPoint());

        if(gamesDAO.getGames().size() != 2 )
            fail();

        gamesDAO.deleteGame(gameId);

        if (gamesDAO.getGames().size() !=1 ){
            fail();
        }
        gamesDAO.deleteGames();

        if (gamesDAO.getGames().size() !=0 ){
            fail();
        }

        sqlProvider.commitTransaction();;
    }


    private boolean compareGame(GameDTO rec1, GameDTO rec2){
        if (rec1.getName().equals(rec2.getName()) &&
           rec1.getInitialPoint().equals(rec2.getInitialPoint()) &&
           rec1.getCheckPoint().equals(rec2.getCheckPoint()) &&
           rec1.getCommandList().equals(rec1.getCommandList()) &&
           rec1.getCheckPointCursor() == rec2.getCheckPointCursor()){

            return true;
        }
        return false;
    }

    @After
    public void tearDown() throws Exception {

    }


}