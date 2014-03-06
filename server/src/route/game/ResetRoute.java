package route.game;

import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class ResetRoute extends CoreRoute {

    @Override
    public void attach() {
        get(new Route("/game/reset") {
            @Override
            public Object handle(Request request, Response response) {
                return "Game Reset Test";
            }
        });
    }
}
