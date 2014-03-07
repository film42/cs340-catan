package route.util;

import model.facade.UtilFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class ChangeLogLevelRoute extends CoreRoute {
    private UtilFacade m_utilFacade;
    public ChangeLogLevelRoute(UtilFacade gamesFacade) {
        m_utilFacade = gamesFacade;
    }
    @Override
    public void attach() {
        get(new Route("/util/changeLogLevel") {
            @Override
            public Object handle(Request request, Response response) {
                boolean modelResponse = m_utilFacade.onChangeLogLevel();

                return "Util Change Log Level Test";
            }
        });
    }
}
