package route.games;

import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class ListRoute extends CoreRoute {

    @Override
    public void attach() {
        get(new Route("/games/list") {
            @Override
            public Object handle(Request request, Response response) {
                return "Games List Test";
            }
        });
    }
}
