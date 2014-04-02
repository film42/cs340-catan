package persistance;


import modelInterfaces.users.User;

import java.util.List;

public interface UsersDba {

	/**
	 * Gets the current users in the db
	 * 
	 * @return a List of User objects currently saved in the db
	 */
	public List<User> getUsers();

	/**
	 * Adds a user to the db
	 */
	public void addUser();
}
