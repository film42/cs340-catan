package route.user;

import model.facade.UtilFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginRoute extends CoreRoute {
    private UtilFacade m_utilFacade;
    public LoginRoute(UtilFacade gamesFacade) {
        m_utilFacade = gamesFacade;
    }
    @Override
    public void attach() {
        get(new Route("/user/login") {
            @Override
            public Object handle(Request request, Response response) {

                boolean modelResponse = m_utilFacade.onUserLogin();
                if(modelResponse){
                    //default return HTTP_OK
                    return "";
                }
                else{
                    response.status(401);
                    return "";
                }
            }
        });
    }
}
