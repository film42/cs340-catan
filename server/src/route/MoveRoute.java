package route;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class MoveRoute extends CoreRoute{
    @Override
    public void attach() {
        post(new Route("/move/") {
            @Override
            public Object handle(Request request, Response response) {
                return "move Test";
            }
        });
    }
}
