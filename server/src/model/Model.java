package model;

import com.google.gson.Gson;

/**
 * Created by qzcx on 3/7/14.
 */
public class Model implements JsonSerializable, JsonParseable {

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
