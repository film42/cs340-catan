package model;

import com.google.gson.Gson;
<<<<<<< HEAD
=======
import com.google.inject.Inject;
>>>>>>> af219cb8c5a92e5ea92a73b709db25b76318734c

/**
 * Created by qzcx on 3/7/14.
 */
public class Model implements JsonSerializable, JsonParseable {

	@Inject
	public Model() {

	}

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

}
