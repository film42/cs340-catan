package persistence;


import java.io.File;
import java.sql.*;

public class SQLPersistenceProvider implements PersistenceProvider {

    private static Connection connection;
    private GamesDAO gamesDAO;
    private UsersDAO usersDAO;

    private static boolean  dbInitialize = false;

    public SQLPersistenceProvider() {
        connection = null;
        gamesDAO = new SQLGamesDAO();
        usersDAO = new SQLUsersDAO();
    }

    public static void initialize(){

        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
            dbInitialize = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){

        String dbName = "db" + File.separator + "catan.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbName;

        if (dbInitialize == false)
            initialize();

        try{

            if (connection == null){
                connection = DriverManager.getConnection(connectionURL);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public UsersDAO getUsersDAO() {
        return this.usersDAO;
    }

    @Override
    public GamesDAO getGamesDAO() {
        return this.gamesDAO;
    }

    /**
	 * Begins a transaction in this SQLPersistenceProvider. Throws an error if already began a transaction.
	 */
	public void beginTransaction(){
        getConnection();
    }

    @Override
    public void commitTransaction() {
        endTransaction(true);

    }

    @Override
    public void rollbackTransaction() {
        endTransaction(false);

    }

    @Override
    public void wipeDatabase() {
       this.gamesDAO.clearDatabase();
       this.usersDAO.clearDatabase();
    }

    /**
	 * if commit is true, commit. otherwise rollback.
   	 */
	public void  endTransaction(boolean commit){
        assert(connection != null);

        try{
            if (commit){
                connection.commit();
            }
            else{
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(connection);
        }
        connection = null;
    }

    /**
     * close PrepareStatement
     * @param stmt
     */
    public static void close(PreparedStatement stmt){
        if (stmt != null){
            try{
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * close ResultSet
     * @param rs
     */
    public static void close(ResultSet rs){
        if (rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Close Connection
     * @param conn
     */
    public static void close(Connection conn)  {
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
