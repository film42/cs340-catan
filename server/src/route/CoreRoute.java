package route;

import spark.*;

public abstract class CoreRoute implements Routable {

    public void get(Route route) {
        spark.Spark.get(route);
    }

    public void post(Route route) {
        spark.Spark.post(route);
    }

}
