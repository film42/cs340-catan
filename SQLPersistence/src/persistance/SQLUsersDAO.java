package persistance;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import copied.*;

public class SQLUsersDAO implements copied.UsersDAO {

	/**
	 * Gets the current users in the db
	 * 
	 * @return a List of User objects currently saved in the db
	 */
	public List<persistance.UserDTO> getUsers(){

        List<persistance.UserDTO> usersDTO = new ArrayList<persistance.UserDTO>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String query = "SELECT id, username, password FROM users order by id";
            stmt = SQLPersistenceProvider.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
              persistance.UserDTO userDTO = new persistance.UserDTO(rs.getInt(1),
                                            rs.getString(2),
                                            rs.getString(3));
              usersDTO.add(userDTO);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            SQLPersistenceProvider.close(stmt);
            SQLPersistenceProvider.close(rs);
        }
        return usersDTO;
    }

	/**
	 * Adds a user to the db
	 */
	public boolean addUser(UserDTO user){
        boolean isUserAdded = false;
        PreparedStatement stmt = null;

        try{
            String query = "INSERT INTO users (id, username, password) values(?,?,?)";
            stmt = SQLPersistenceProvider.getConnection().prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            if (stmt.executeUpdate() == 1) {
                isUserAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            SQLPersistenceProvider.close(stmt);
        }
        return isUserAdded;
    }
    /**
     * delete Users
     */
    public void deleteUsers(){

        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM users";
            stmt = SQLPersistenceProvider.getConnection().prepareStatement(query);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLPersistenceProvider.close(stmt);
        }
    }

}
