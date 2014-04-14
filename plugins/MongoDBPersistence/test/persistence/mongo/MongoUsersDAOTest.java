package persistence.mongo;

import org.junit.Test;
import persistence.GamesDAO;
import persistence.PersistenceProvider;
import persistence.UserDTO;
import persistence.UsersDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by: film42 on: 4/14/14.
 */
public class MongoUsersDAOTest {

    private PersistenceProvider provider = new MongoPersistenceProvider();

    private GamesDAO gamesDAO;
    private UsersDAO usersDAO;

    UserDTO userDTO1;
    UserDTO userDTO2;

    @org.junit.Before
    public void setUp() throws Exception {

        // Connect to test DB
        Connection conn = new Connection("","","localhost","catan_test",27017);
        MongoPersistenceProvider.getConnection(conn);

        gamesDAO = provider.getGamesDAO();
        usersDAO = provider.getUsersDAO();

        userDTO1 = new UserDTO(1 ,"June", "june");
        userDTO2 = new UserDTO(2 ,"Tang", "tang");

    }

    @org.junit.After
    public void tearDown() throws Exception {
        provider.wipeDatabase();
    }

    //
    // Core
    //

    @Test
    public void testGetUsers() throws Exception {
        assertTrue(usersDAO.addUser(userDTO1));
        assertTrue(usersDAO.addUser(userDTO2));

        List<UserDTO> usersDTO = usersDAO.getUsers();

        if (usersDTO.size() == 2){
            assertTrue(true);
        }
        else {
            assertTrue(false);
        }
        assertEquals(usersDTO.get(0).getUsername(), userDTO1.getUsername());
        assertEquals(usersDTO.get(0).getPassword(), userDTO1.getPassword());

        assertEquals(usersDTO.get(1).getUsername(), userDTO2.getUsername());
        assertEquals(usersDTO.get(1).getPassword(), userDTO2.getPassword());

    }

    @Test
    public void testAddUser() throws Exception {
        assertTrue(usersDAO.addUser(userDTO1));
        assertTrue(usersDAO.addUser(userDTO2));
    }

    @Test
    public void testClearDatabase() throws Exception {
        usersDAO.clearDatabase();

        // Ensure no games
        assertEquals(0, usersDAO.getUsers().size());
    }
}
