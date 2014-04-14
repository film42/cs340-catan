package persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import persistence.GamesDAO;
import persistence.PersistenceProvider;
import persistence.UsersDAO;

import java.net.UnknownHostException;

/**
 * Created by: film42 on: 4/12/14.
 */
public class MongoPersistenceProvider implements PersistenceProvider {

    private static MongoClient mongoClient;
    private GamesDAO gamesDAO;
    private UsersDAO usersDAO;

    /**
     * Get the DB from MongoClient via its connection open connection
     *
     * @return MongoClient
     */
    public static DB getConnection(Connection conn) {
        // Return DB if we've established a connection
        if(mongoClient != null) {
            DB db = mongoClient.getDB(conn.getDatabaseName());
            db.authenticate(conn.getUsername(), conn.getPassword());
            return db;
        }

        // Otherwise create a new Client and connect
        try {
            mongoClient = new MongoClient(conn.getHost(), conn.getPort());
            DB db = mongoClient.getDB(conn.getDatabaseName());
            db.authenticate(conn.getUsername(), conn.getPassword());
            return db;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DB getConnection() {
        Connection conn = Connection.fromLocalSettings();
        return getConnection(conn);
    }

    /**
     * Get a collection from DB
     * @param collection collection name
     * @return DBCollection of collection passed
     */
    public static DBCollection getCollection(String collection) {
        DB db = getConnection();
        return db.getCollection(collection);
    }


    public MongoPersistenceProvider() {
        this.gamesDAO = new MongoGamesDAO();
        this.usersDAO = new MongoUsersDAO();
    }

    @Override
    public UsersDAO getUsersDAO() {
        return this.usersDAO;
    }

    @Override
    public GamesDAO getGamesDAO() {
        return this.gamesDAO;
    }

    @Override
    public void beginTransaction() {
        getConnection();
    }

    @Override
    public void commitTransaction() {
//        mongoClient.close();
    }

    @Override
    public void rollbackTransaction() {
        // Sorry :(
    }

    @Override
    public void wipeDatabase() {
        gamesDAO.clearDatabase();
        usersDAO.clearDatabase();
    }

    public static boolean setField(String collection, int gameId, String field, Object value) {
        DBCollection coll = MongoPersistenceProvider.getCollection(collection);

        // Build Query
        BasicDBObject query = new BasicDBObject("id", gameId);
        // Get cursor of item
        BasicDBObject obj = (BasicDBObject)coll.findOne(query);

        if(obj == null) return false;

        obj.append(field, value);

        coll.save(obj); // NOT ATOMIC

        return true;
    }

    public static Object getField(String collection, int gameId, String fieldName) {
        DBCollection coll = MongoPersistenceProvider.getCollection(collection);

        // Build Query
        BasicDBObject query = new BasicDBObject("id", gameId);
        // Get cursor of item
        BasicDBObject obj = (BasicDBObject)coll.findOne(query);

        // Null if not found
        if(obj == null) return null;

        // Return cast to String. Default obj in case of nested DBObjects
        return obj.get(fieldName);
    }
}
