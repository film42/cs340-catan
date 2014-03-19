package server;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.setPort;

<<<<<<< HEAD
import java.util.logging.Logger;

=======
import comm.request.CreateGameRequest;
>>>>>>> FETCH_HEAD
import model.InjectorFactory;
import model.Model;
import model.ModelModule;
import model.facade.GameFacade;
import model.facade.GamesFacade;
import model.facade.MoveFacade;
import model.facade.UtilFacade;
import route.MoveRoute;
import route.game.AddAIRoute;
import route.game.CommandsRoute;
import route.game.ListAIRoute;
import route.game.ModelRoute;
import route.game.ResetRoute;
import route.games.CreateRoute;
import route.games.JoinRoute;
import route.games.ListRoute;
import route.user.LoginRoute;
import route.user.RegisterRoute;
import route.util.ChangeLogLevelRoute;

import com.google.inject.Injector;
import comm.request.CreateGameRequest;

public class Server {

	// Logger
	private static Logger log;
	static {
		log = Logger.getLogger("server");
	}

    private void run() {
    }

    private void config() {
		Server.log.info("Configuring server...");

        // Set port here
        setPort(8081);

        // Set static directory to "gameplay"
        externalStaticFileLocation("../gameplay");

		// Google Guice injection
		Injector injector = InjectorFactory.createInjector(new ModelModule());

        // Facade Classes
		Model myGame = injector.getInstance(Model.class);
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
        new AddAIRoute(myGameFacade).attach();
        new CommandsRoute(myGameFacade).attach();
        new MoveRoute(myMoveFacade).attach();
        new ChangeLogLevelRoute(myUtilFacade).attach();

		Server.log.info("Server configured");

        // TODO: REMOVE!
        myGame.createGame(new CreateGameRequest(true, true, true, "Demo made by server"));
    }

    public static void main(String[] args) {
		// Server
        Server server = new Server();
        server.config();
        server.run();
    }

}
