package persistance;


import java.util.List;

public interface UsersDAO {

	/**
	 * Gets the current users in the db
	 * 
	 * @return a List of User objects currently saved in the db
	 */
	public List<UserDTO> getUsers();

	/**
	 * Adds a user to the db
	 */
	public void addUser(UserDTO user);
}
