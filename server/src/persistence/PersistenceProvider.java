package persistence;

public interface PersistenceProvider {

	/**
	 * Gets the users Dba from the PersistenceProvider
	 * 
	 * @return the UsersDAO object
	 */
	public UsersDAO getUsersDBA();

	/**
	 * Gets the Games Dba from the PersistenceProvider
	 * 
	 * @return the GamesDAO object
	 */
	public GamesDAO getGamesDBA();

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
