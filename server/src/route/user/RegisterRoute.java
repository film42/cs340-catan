package route.user;

import model.facade.UtilFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;
/**
 * Created by Jon George on 3/6/14.
 */
public class RegisterRoute extends CoreRoute {
    private UtilFacade m_utilFacade;
    public RegisterRoute(UtilFacade gamesFacade) {
        m_utilFacade = gamesFacade;
    }

    @Override
    public void attach() {
        get(new Route("/user/register") {
            @Override
            public Object handle(Request request, Response response) {
                boolean modelResponse = m_utilFacade.onUserRegister();
                return "User Register Test";
            }
        });
    }
}
