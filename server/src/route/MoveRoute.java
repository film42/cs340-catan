package route;

import model.facade.GamesFacade;
import model.facade.MoveFacade;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by qzcx on 3/6/14.
 */
public class MoveRoute extends CoreRoute{
    private MoveFacade m_movesFacade;
    public MoveRoute(MoveFacade moveFacade) {
        m_movesFacade = moveFacade;
    }
    @Override
    public void attach() {
        post(new Route("/moves/*") {
            @Override
            public Object handle(Request request, Response response) {

                return m_movesFacade.onMove();
            }
        });
    }
}
