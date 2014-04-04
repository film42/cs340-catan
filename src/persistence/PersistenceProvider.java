package persistence;

public interface PersistenceProvider {

	/**
	 * Gets the users Dba from the PersistenceProvider
	 * 
	 * @return the UsersDao object
	 */
	public UsersDao getUsersDba();

	/**
	 * Gets the Games Dba from the PersistenceProvider
	 * 
	 * @return the GamesDao object
	 */
	public GamesDao getGamesDba();

	/**
	 * Begins a transaction in this PersistenceProvider. Throws an error if already began a transaction.
	 */
	public void beginTransaction();
	
	/**
	 * Commits the transaction to the database. Must have called beginTransaction() previously.
	 */
	public void commitTransaction();
	
	/**
	 * Deletes all changes made since the last beginTransaction().
	 */
	public void rollbackTransaction();

	/**
	 * Wipes the database immediately
	 */
	public void wipeDatabase();
}
