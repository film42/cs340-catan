package route.games;

import model.facade.GamesFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;
import comm.request.JoinGameRequest;

/**
 * Created by Jon George on 3/6/14.
 */
public class JoinRoute extends CoreRoute {
    private GamesFacade m_gamesFacade;
    public JoinRoute(GamesFacade gamesFacade) {
        m_gamesFacade = gamesFacade;
    }
    @Override
    public void attach() {
        post(new Route("/games/join") {
            @Override
            public Object handle(Request request, Response response) {
                String color = request.params("color");
                String id = request.params("id");
                
                if (color == null || id == null){
                    response.status(400);
                    return("Failed to join game");
                }
                JoinGameRequest joinGameRequest = new JoinGameRequest(color, id);
                Boolean modelResponse = m_gamesFacade.onJoinGame(joinGameRequest);
                if(modelResponse){
                    response.cookie("catan.game", "-1");
                    return "";
                }else{
                    response.status(401);
                    return "";
                }
            }
        });
    }
}
