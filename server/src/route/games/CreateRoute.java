package route.games;

import model.facade.GamesFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class CreateRoute extends CoreRoute {
    private GamesFacade m_gamesFacade;
    public CreateRoute(GamesFacade gamesFacade) {
        m_gamesFacade = gamesFacade;
    }
    @Override
    public void attach() {
        post(new Route("/games/create") {
            @Override
            public Object handle(Request request, Response response) {
                String modelResponse = m_gamesFacade.onCreateGame();
                return modelResponse;
            }
        });
    }
}
