package Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.SQLPersistenceProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import persistence.SQLUsersDAO;
import persistence.UserDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import persistence.PersistenceProvider;

public class UsersDAOTest {

    SQLUsersDAO usersDAO;

    UserDTO userDTO1;
    UserDTO userDTO2;
    PersistenceProvider sqlProvider;

    @Before
    public void setUp() throws Exception {
        sqlProvider = new SQLPersistenceProvider();
        usersDAO = new SQLUsersDAO();
        userDTO1 = new UserDTO(1,"June", "june");
        userDTO2 = new UserDTO(2,"Tang", "tang");

    }

    @Test
    public void test_addUser() {
        // add users

        sqlProvider.beginTransaction();
        usersDAO.deleteUsers();
        assertTrue(usersDAO.addUser(userDTO1));
        assertTrue(usersDAO.addUser(userDTO2));
        sqlProvider.commitTransaction();
    }

    @Test
    public void test_getUsers() {
        //test getUsers
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();
        sqlProvider.beginTransaction();
        usersDTO = usersDAO.getUsers();
        sqlProvider.commitTransaction();

        //System.out.println("record " + usersDTO.size());
        if (usersDTO.size() == 2){
            assertTrue(true);
        }
        else {
            assertTrue(false);
        }
        assertEquals(usersDTO.get(0).getUsername(),userDTO1.getUsername());
        assertEquals(usersDTO.get(0).getPassword(),userDTO1.getPassword());

        assertEquals(usersDTO.get(1).getUsername(),userDTO2.getUsername());
        assertEquals(usersDTO.get(1).getPassword(),userDTO2.getPassword());
     }

    @After
    public void tearDown() throws Exception {

    }


}
