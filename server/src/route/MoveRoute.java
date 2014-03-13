package route;

import comm.moves.Command;
import comm.moves.InvalidCommandException;
import comm.moves.RollNumber;
import comm.moves.SendChat;
import model.facade.MoveFacade;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;

/**
 * Created by qzcx on 3/6/14.
 */
public class MoveRoute extends CoreRoute {

    final String SEND_CHAT = "/moves/sendChat";
    final String ROLL_NUMBER = "/moves/rollNumber";

    private MoveFacade m_movesFacade;
    public MoveRoute(MoveFacade moveFacade) {
        m_movesFacade = moveFacade;
    }
    
    @Override
    public void attach() {
        post(new Route("/moves/*") {
            @Override
            public Object handle(Request request, Response response) {
                String json = request.body();
                String url = request.pathInfo();

//                try {
//                    //
//                    // Switching on all possible commands
//                    //
//                    switch (url) {
//                        case SEND_CHAT:
//                            SendChat sCR = SendChat.fromJson(json);
//                            m_movesFacade.onMove(sCR);
//                            break;
//                        case ROLL_NUMBER:
//                            RollNumber rN = RollNumber.fromJson(json);
//                            m_movesFacade.onMove(rN);
//                            break;
//                        default:
//                            response.status(404);
//                            return "";
//                    }
//
//                    // Once we've switched, return ok
//                    return "Done!";
//                } catch (InvalidCommandException e) {
//                    // Valid but can't run
//                    return "Syntactically Correct Command is Not Valid.";
//                } catch (IOException e) {
//                    // Server Error
//                    response.status(500);
//                    return "Error: Server Error!";
//                }
                return "";
            }
        });
    }
}
