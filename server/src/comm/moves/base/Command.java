package comm.moves.base;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by: film42 on: 3/12/14.
 */
public abstract class Command implements Commandable {

	protected String type;

	protected int playerIndex;

    public String getType() {
        return type;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }


    public static <T extends Command> T moveFromJson(String json, Class<T> type) throws JsonSyntaxException {
        Gson gson = new Gson();
        return type.cast(gson.fromJson(json, type));
    }
}
