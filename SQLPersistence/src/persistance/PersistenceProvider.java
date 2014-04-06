package persistance;

import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;

import java.net.ConnectException;
import java.util.List;

public class PersistenceProvider {

    private static Connection connection;


    public PersistenceProvider() {
        connection = null;

    }

    public static boolean initialize(){

        boolean isInitialize = true;

        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            isInitialize = false;
            e.printStackTrace();
        }
        return isInitialize;

    }


    public static Connection getConnection(){
        return connection;
    }
	/**
	 * Begins a transaction in this PersistenceProvider. Throws an error if already began a transaction.
	 */
	public static boolean beginTransaction(){
        boolean isBeginTransaction = true;

        String dbName = "db" + File.separator + "catan.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbName;

        try{

            if (connection == null){
                connection = DriverManager.getConnection(connectionURL);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            isBeginTransaction = false;
        }
        return isBeginTransaction;
    }
	
	/**
	 * if commit is true, commit. otherwise rollback.
   	 */
	public static void  endTransaction(boolean commit){
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


    public static void main(String[] args){

         initialize();
         beginTransaction();
         if(getConnection() != null)
             System.out.println("connected");
         else
            System.out.println("not connected");
         endTransaction(true);

    }


}
