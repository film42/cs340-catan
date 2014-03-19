package comm.moves;

import comm.moves.base.Command;
import comm.moves.base.InvalidCommandException;
import model.messaging.LineImpl;
import modelInterfaces.base.GameInfo;
import modelInterfaces.messaging.Chat;
import modelInterfaces.messaging.Line;


import java.io.IOException;

/**
 * Created by: film42 on: 3/12/14.
 */
public class SendChat extends Command {

    private String content;

    public String getContent() {
        return content;
    }

    @Override
    public void execute(GameInfo gameInfo) throws IOException, InvalidCommandException {
       Chat  chat = gameInfo.getData().getChat();
       String user = gameInfo.getData().getPlayers().get(getPlayerIndex()).getName();
       chat.addLine(new LineImpl(user,content));

    }
}
