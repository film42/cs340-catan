package route.user;

import model.facade.UtilFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;
import comm.request.UserRequest;
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
        post(new Route("/user/register") {
            @Override
            public Object handle(Request request, Response response) {

                String username = request.queryParams("username");
                String password = request.queryParams("password");

                if (username == null || password == null){
                  response.status(401);
                  return ("Failed to Register - invalid username or password.");
                }
                UserRequest userRequest = new UserRequest(username, password);
                boolean modelResponse = m_utilFacade.onUserRegister(userRequest);

                if(modelResponse){
                    int id = m_utilFacade.getUserId(username, password);
                    response.status(200);
                    addCookie(response, "catan.user", "{ username : " + username + " , password : " + password + " , id : " + id + " }");
                    addCookie(response, "catan.username", username);
                    return "";
                }else{
                    response.status(401);
                    return "Failed to register - someone already has that username.";
                }
            }
        });
    }
}
