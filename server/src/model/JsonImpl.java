package model;

import com.google.gson.Gson;

/**
 * Created by Jon George on 3/13/14.
 */
public class JsonImpl implements JsonSerializable, JsonParseable{


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
