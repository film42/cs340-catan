package comm.moves;

import comm.generic.BaseRequest;

/**
 * Created by: film42 on: 3/12/14.
 */
public class SendChat extends Command {

    private String content;

    public String getContent() {
        return content;
    }

    // Json Parser Specific
    public static SendChat fromJson(String json) {
        return fromJson(json, SendChat.class);
    }
}
