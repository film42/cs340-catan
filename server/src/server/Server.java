package server;

import static spark.Spark.*;

import model.IModel;
import model.Model;
import model.facade.GameFacade;
import model.facade.GamesFacade;
import model.facade.MoveFacade;
import model.facade.UtilFacade;
import route.MoveRoute;
import route.assets.StaticRoute;
import route.game.*;
import route.games.CreateRoute;
import route.games.JoinRoute;
import route.games.ListRoute;
import route.user.LoginRoute;
import route.user.RegisterRoute;

import java.io.File;

public class Server {

    private void run() {
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.config();
        server.run();
    }

    private void config() {
        setPort(8081);

        externalStaticFileLocation("../gameplay");
        IModel myGame = new Model();
        UtilFacade myUtilFacade = new UtilFacade(myGame);
        GamesFacade myGamesFacade = new GamesFacade(myGame);
        GameFacade myGameFacade = new GameFacade(myGame);
        MoveFacade myMoveFacade = new MoveFacade(myGame);

        // Each Route
        new LoginRoute(myUtilFacade).attach();
        new RegisterRoute(myUtilFacade).attach();
        new ListRoute(myGamesFacade).attach();
        new JoinRoute(myGamesFacade).attach();
        new CreateRoute(myGamesFacade).attach();
        new ResetRoute(myGameFacade).attach();
        new ModelRoute(myGameFacade).attach();
        new ListAIRoute(myGameFacade).attach();
        new addAIRoute(myGameFacade).attach();
        new CommandsRoute(myGameFacade).attach();
        new MoveRoute(myMoveFacade).attach();
        // Statics do this last
        //new StaticRoute().attach();
    }

}
