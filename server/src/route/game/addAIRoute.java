package route.game;

import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class addAIRoute extends CoreRoute {
    @Override
    public void attach() {
        get(new Route("/game/addAI") {
            @Override
            public Object handle(Request request, Response response) {
                return "Game addAI Test";
            }
        });
    }
}
