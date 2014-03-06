package route.user;

import route.CoreRoute;
import route.Routable;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginRoute extends CoreRoute {

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
