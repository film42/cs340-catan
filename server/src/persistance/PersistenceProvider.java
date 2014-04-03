package persistance;

public abstract class PersistenceProvider {

	/**
	 * Creates an instance of our provider in static context.
	 * 
	 * @param the
	 *            name of the provider from the list of working provider names
	 */
	public static void createProvider(String providerName) {
		// implemented later
	};

	/**
	 * Gets the provider from static context. Throws an error if there is none.
	 * 
	 * @return the Persistence Provider from the static context
	 */
	public static PersistenceProvider getProvider() {
		// implemented later
		return null;
	};

	/**
	 * Gets the users Dba from the PersistenceProvider
	 * 
	 * @return the UsersDba object
	 */
	public UsersDba getUsersDba() {
		// implemented later
		return null;
	}

	/**
	 * Gets the Games Dba from the PersistenceProvider
	 * 
	 * @return the GamesDba object
	 */
	public GamesDba getGamesDba() {
		// implemented later
		return null;
	}

	/**
	 * Begins a transaction in this PersistenceProvider. Throws an error if already began a transaction.
	 */
	public abstract void beginTransaction();
	
	/**
	 * Commits the transaction to the database. Must have called beginTransaction() previously.
	 */
	public abstract void commitTransaction();
	
	/**
	 * Deletes all changes made since the last beginTransaction().
	 */
	public abstract void rollbackTransaction();

	/**
	 * Wipes the database immediately
	 */
	public abstract void wipeDatabase();
}
