package persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import persistence.UserDTO;
import persistence.UsersDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 4/12/14.
 */
public class MongoUsersDAO implements UsersDAO {

    private final String COLLECTION = "users";

    @Override
    public List<UserDTO> getUsers() {
        DBCollection coll = MongoPersistenceProvider.getCollection(COLLECTION);

        // Create our new empty list of IDs
        List<UserDTO> users = new ArrayList<>();

        // Iterate through collection, saving each ID
        try (DBCursor cursor = coll.find()) {
            while (cursor.hasNext()) {
                // Get DBObj from cursor
                DBObject obj = cursor.next();

                // Get the fields
                int id = (Integer)obj.get("id");
                String username = (String) obj.get("username");
                String password = (String) obj.get("password");

                // Create the DTO
                UserDTO user = new UserDTO(id, username, password);

                // Add to list
                users.add(user);
            }
        }

        // Return fancy users list
        return users;
    }

    @Override
    public boolean addUser(UserDTO user) {
        DBCollection coll = MongoPersistenceProvider.getCollection(COLLECTION);

        int id = user.getId();

        BasicDBObject obj = new BasicDBObject("id", id);
            obj.append("username", user.getUsername());
            obj.append("password", user.getPassword());

        coll.insert(obj);

        return true;
    }

    @Override
    public void clearDatabase() {
        DBCollection coll = MongoPersistenceProvider.getCollection(COLLECTION);

        // Drop the collection
        coll.drop();
    }
}
