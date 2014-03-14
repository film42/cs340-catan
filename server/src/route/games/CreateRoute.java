package route.games;

import model.facade.GamesFacade;
import route.CoreRoute;
import spark.Request;
import spark.Response;
import spark.Route;
import comm.request.CreateGameRequest;

/**
 * Created by qzcx on 3/6/14.
 */
public class CreateRoute extends CoreRoute {
    private GamesFacade m_gamesFacade;
    public CreateRoute(GamesFacade gamesFacade) {
        m_gamesFacade = gamesFacade;
    }
    @Override
    public void attach() {
        post(new Route("/games/create") {
            @Override
            public Object handle(Request request, Response response) {
                boolean randomTitles = false;
                boolean randomNumbers = false;
                boolean randomPorts = false;


                if (request.params("name") == null){
                    response.status(400);
                    return("Invalid Game Name.");
                }
                if (request.params("randomTiles").equals("true"));
                    randomTitles = true;

                if (request.params("randomNumbers").equals("true"));
                   randomNumbers = true;

                if (request.params("randomPorts").equals("true"));
                    randomPorts = true;


                CreateGameRequest createGameRequest = new CreateGameRequest(randomTitles,randomNumbers,randomPorts,
                                                                            request.params("name"));
                String modelResponse = m_gamesFacade.onCreateGame(createGameRequest);
                return modelResponse;
            }
        });
    }
}
