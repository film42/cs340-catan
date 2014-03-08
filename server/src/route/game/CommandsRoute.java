package route.game;

import model.facade.GameFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class CommandsRoute extends CoreRoute {
    private GameFacade m_gameFacade;
    public CommandsRoute(GameFacade gameFacade) {
        m_gameFacade = gameFacade;
    }
    @Override
    public void attach() {
        get(new Route("/game/commands") {
            @Override
            public Object handle(Request request, Response response) {
                String modelResponse = m_gameFacade.onGetCommands();
                return modelResponse;
            }
        });
        post(new Route("/game/commands") {
            @Override
            public Object handle(Request request, Response response) {
                boolean modelResponse = m_gameFacade.onPostCommands();
                return "Game Commands POST Test";
            }
        });
    }
}
