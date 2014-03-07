package route.games;

import model.facade.GamesFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class JoinRoute extends CoreRoute {
    private GamesFacade m_gamesFacade;
    public JoinRoute(GamesFacade gamesFacade) {
        m_gamesFacade = gamesFacade;
    }
    @Override
    public void attach() {
        get(new Route("/games/join") {
            @Override
            public Object handle(Request request, Response response) {
                return "Games Join Test";
            }
        });
    }
}
