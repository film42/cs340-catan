package persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import persistence.GamesDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 4/12/14.
 */
public class MongoGamesDAO implements GamesDAO {

    private final String COLLECTION = "games";

    private boolean setField(int gameId, String field, Object value) {
        return MongoPersistenceProvider.setField(COLLECTION, gameId, field, value);
    }

    private String getStringField(int gameId, String fieldName) {
        return (String)MongoPersistenceProvider.getField(COLLECTION, gameId, fieldName);
    }

    private Integer getIntegerField(int gameId, String fieldName) {
        return (Integer)MongoPersistenceProvider.getField(COLLECTION, gameId, fieldName);
    }

    @Override
    public List<Integer> getGameIds() {
        DBCollection coll = MongoPersistenceProvider.getCollection(COLLECTION);

        // Create our new empty list of IDs
        List<Integer> ids = new ArrayList<Integer>();

        // Iterate through collection, saving each ID
        try (DBCursor cursor = coll.find()) {
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                int id = (Integer) obj.get("id");
                ids.add(id);
            }
        }

        return ids;
    }

    @Override
    public String getName(int gameId) {
        return getStringField(gameId, "name");
    }

    @Override
    public String getInitialModel(int gameId) {
        return getStringField(gameId, "initialModel");
    }

    @Override
    public int addGame(int gameId, String name, String initialModel) {

        DBCollection coll = MongoPersistenceProvider.getCollection(COLLECTION);

        BasicDBObject obj = new BasicDBObject("id", gameId);
            obj.append("name", name);
            obj.append("initialModel", initialModel);
            obj.append("checkPoint", null);
            obj.append("commandList", null);
            obj.append("checkPointCursor", 0);

        coll.insert(obj); // NOT ATOMIC

        return (Integer)obj.get( "id" );
    }

    @Override
    public boolean joinGame(int gameId, String initialModel) {
        return setField(gameId, "initialModel", initialModel);
    }

    @Override
    public String getCommandList(int gameId) {
        return getStringField(gameId, "commandList");
    }

    @Override
    public boolean updateCommandList(int gameId, String commandListJson) {
        return setField(gameId, "commandList", commandListJson);
    }

    @Override
    public String getCheckpoint(int gameId) {
        return getStringField(gameId, "checkPoint");
    }

    @Override
    public boolean updateCheckpoint(int gameId, String checkpointModel, int cursor) {
        boolean u1 = setField(gameId, "checkPoint", checkpointModel);
        boolean u2 = setField(gameId, "checkPointCursor", cursor);

        return u1 && u2;
    }

    @Override
    public int getCursor(int id) {
        return getIntegerField(id, "checkPointCursor");
    }

    @Override
    public void clearDatabase() {
        DBCollection coll = MongoPersistenceProvider.getCollection(COLLECTION);

        // Drop the collection
        coll.drop();
    }
}
