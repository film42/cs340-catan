package route.user;

import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;
/**
 * Created by Jon George on 3/6/14.
 */
public class RegisterRoute extends CoreRoute {

    @Override
    public void attach() {
        get(new Route("/user/register") {
            @Override
            public Object handle(Request request, Response response) {
                return "User Register Test";
            }
        });
    }
}
