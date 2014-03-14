package comm.moves.base;

import com.google.gson.Gson;

/**
 * Created by: film42 on: 3/12/14.
 */
public abstract class Command implements Commandable {

    private String type;

    private int playerIndex;

    public String getType() {
        return type;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }


    public static <T extends Command> T moveFromJson(String json, Class<T> type) {
        Gson gson = new Gson();
        return type.cast(gson.fromJson(json, type));
    }
}
