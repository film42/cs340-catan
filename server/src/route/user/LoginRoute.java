package route.user;

import model.facade.UtilFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;
import comm.request.UserRequest;

public class LoginRoute extends CoreRoute {
    private UtilFacade m_utilFacade;
    public LoginRoute(UtilFacade gamesFacade) {
        m_utilFacade = gamesFacade;
    }
    @Override
    public void attach() {
        post(new Route("/user/login") {
            @Override
            public Object handle(Request request, Response response) {

                string username = request.params("username");
                string password = request.params("password");

                if (username != null && password != null){
                  response.status(401);
                  return "Failed to login - invalid username or password.");
                }

                UserRequest userRequest = new UserRequest(username, password);
                boolean modelResponse = m_utilFacade.onUserLogin(userRequest);
                if(modelResponse){
                  //default return HTTP_OK
                  response.status(200);
                  response.cookie("catan.user", "username:" +username +" password:"+password);
                  return "";
                }
                else{
                    response.status(401);
                    return "Failed to login - bad username or password.");
                }
            }
        });
    }
}
