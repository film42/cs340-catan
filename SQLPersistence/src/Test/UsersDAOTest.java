package Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistance.PersistenceProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistance.PersistenceProvider;
import persistance.UsersDAO;
import persistance.UserDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersDAOTest {

    UsersDAO usersDAO;

    UserDTO userDTO1;
    UserDTO userDTO2;

    @Before
    public void setUp() throws Exception {
        PersistenceProvider.initialize();
        usersDAO = new UsersDAO();
        userDTO1 = new UserDTO(1,"June", "june");
        userDTO2 = new UserDTO(2,"Tang", "tang");

    }

    @Test
    public void test_addUser() {
        // add users

        PersistenceProvider.beginTransaction();
        usersDAO.deleteUsers();
        assertTrue(usersDAO.addUser(userDTO1));
        assertTrue(usersDAO.addUser(userDTO2));
        PersistenceProvider.endTransaction(true);

    }

    @Test
    public void test_getUsers() {
        //test getUsers
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();
        PersistenceProvider.beginTransaction();
        usersDTO = usersDAO.getUsers();
        PersistenceProvider.endTransaction(true);

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
