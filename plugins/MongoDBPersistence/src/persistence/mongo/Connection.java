package persistence.mongo;

/**
 * Created by: film42 on: 4/12/14.
 */
public class Connection {

    private String username;
    private String password;
    private String host;
    private String databaseName;
    private int port;

    public Connection(String username, String password, String host, String databaseName, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.databaseName = databaseName;
        this.port = port;
    }

    public static Connection fromLocalSettings() {
        // Pattern regex = Pattern.compile("mongodb:\\/\\/(\\w+):(.*)@(.*):(\\d+)\\/(.*)$");

        if(System.getenv("MONGO_DB_NAME") == null) {

            return new Connection("", "", "localhost", "catan", 27017);
        }

        String user = System.getenv("MONGO_USER");
        String pass = System.getenv("MONGO_PASSWORD");
        String dbName = System.getenv("MONGO_DB_NAME");
        String host = System.getenv("MONGO_HOST");
        int port = Integer.parseInt( System.getenv("MONGO_PORT") );

        return new Connection(user, pass, host, dbName, port);
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password.toCharArray();
    }

    public String getHost() {
        return host;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public int getPort() {
        return port;
    }
}
