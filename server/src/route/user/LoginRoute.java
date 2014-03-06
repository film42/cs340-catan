package route.user;

import route.Core;
import route.Routable;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by film42 on 3/6/14.
 */
public class LoginRoute extends Core implements Routable {

    @Override
    public void attach() {
        get(new Route("/user/login") {
            @Override
            public Object handle(Request request, Response response) {
                return "test";
            }
        });
    }
}
