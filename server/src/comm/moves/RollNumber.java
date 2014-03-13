package comm.moves;

/**
 * Created by: film42 on: 3/12/14.
 */
public class RollNumber extends Command {

    private int number;

    public int getNumber() {
        return number;
    }

    // Json Parser Specific
    public static RollNumber fromJson(String json) {
        return fromJson(json, RollNumber.class);
    }
}
