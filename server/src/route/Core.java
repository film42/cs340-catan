package route;

import spark.*;

public class Core {

    public void get(Route route) {
        spark.Spark.get(route);
    }

    public void post(Route route) {
        spark.Spark.post(route);
    }
}
