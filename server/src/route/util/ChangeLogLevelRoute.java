package route.util;

import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class ChangeLogLevelRoute extends CoreRoute {
    @Override
    public void attach() {
        get(new Route("/util/changeLogLevel") {
            @Override
            public Object handle(Request request, Response response) {
                return "Util Change Log Level Test";
            }
        });
    }
}
