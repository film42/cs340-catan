package route.game;

import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class CommandsRoute extends CoreRoute {
    @Override
    public void attach() {
        get(new Route("/game/commands") {
            @Override
            public Object handle(Request request, Response response) {
                return "Game Commands GET Test";
            }
        });
        post(new Route("/game/commands") {
            @Override
            public Object handle(Request request, Response response) {
                return "Game Commands POST Test";
            }
        });
    }
}
