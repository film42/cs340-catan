package server;

import com.google.inject.Injector;
import comm.request.CreateGameRequest;
import comm.request.JoinGameRequest;
import model.InjectorFactory;
import model.JsonImpl;
import model.Model;
import model.ModelModule;
import model.base.GameImpl;
import model.facade.GameFacade;
import model.facade.GamesFacade;
import model.facade.MoveFacade;
import model.facade.UtilFacade;
import model.map.MapImpl;
import modelInterfaces.base.Game;
import modelInterfaces.base.Player;
import modelInterfaces.base.Resources;
import route.MoveRoute;
import route.game.*;
import route.games.CreateRoute;
import route.games.JoinRoute;
import route.games.ListRoute;
import route.user.LoginRoute;
import route.user.RegisterRoute;
import route.util.ChangeLogLevelRoute;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.setPort;

public class Server {

	// Logger
	public static Logger log;
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
        new CommandsRoute(myMoveFacade).attach();
        new MoveRoute(myMoveFacade).attach();
        new ChangeLogLevelRoute(myUtilFacade).attach();

		Server.log.info("Server configured");

        myGame.createGame(new CreateGameRequest(true, true, true, "Just Started"));
        myGame.joinGame(new JoinGameRequest("red", "0"), "Adam");
		myGame.joinGame(new JoinGameRequest("blue", "0"), "Garrett");
		myGame.joinGame(new JoinGameRequest("orange", "0"), "June");
		myGame.joinGame(new JoinGameRequest("green", "0"), "Steve");

        myGame.createGame(new CreateGameRequest(true, true, true, "Test Game"));
		myGame.joinGame(new JoinGameRequest("red", "1"), "Adam");
        myGame.joinGame(new JoinGameRequest("blue","1"), "Garrett");

		// Past Setup
		myGame.createGame(new CreateGameRequest(true, true, true, "Past Setup"));
		myGame.joinGame(new JoinGameRequest("red", "2"), "Adam");
		myGame.joinGame(new JoinGameRequest("blue", "2"), "June");
		
		// Half way
		myGame.createGame(new CreateGameRequest(true, true, true, "Half Way"));
		myGame.joinGame(new JoinGameRequest("red", "3"), "Adam");
		myGame.joinGame(new JoinGameRequest("blue", "3"), "Garrett");
		myGame.joinGame(new JoinGameRequest("orange", "3"), "June");
		myGame.joinGame(new JoinGameRequest("green", "3"), "Steve");

		// now load an existing model from a json and put it in our Past Setup game
		try {
			String gameJson = new Scanner(new File("PastSetupGame.json")).useDelimiter("\\Z").next();
			Game game = JsonImpl.fromJson(gameJson, GameImpl.class);
			myGame.getGames().get(2).setData(game);

			gameJson = new Scanner(new File("HalfWayGame.json")).useDelimiter("\\Z").next();
			game = JsonImpl.fromJson(gameJson, GameImpl.class);
			myGame.getGames().get(3).setData(game);

		} catch (IOException e) {
			e.printStackTrace();
		}

        List<Player> players = myGame.findGameById(1).getData().getPlayers();
        for (Player player : players) { //give them lots of resources
            Resources money = injector.getInstance(Resources.class);
            money.setResources(9,9,9,9,9);
            player.setResources(money);
        }
    }

    private static class UserCookie extends JsonImpl{
        private String name;
        private String password;
        private Long playerID;

        private UserCookie(String name, String password, Long playerID) {
            this.name = name;
            this.password = password;
            this.playerID = playerID;
        }
    }

    public static String createUserCookie(String username, String password, int id){
        String s = "{ username : " + username + " , password : " + password + " , playerID : " + id + " }";
        UserCookie cookie = new UserCookie(username,password,(long)id);
        String jsonCookie = cookie.toJson();
        //noinspection deprecation
        return encodeURIComponent(jsonCookie);
    }

    /**
     * utility function used to encode cookie strings
     * I got this from stack overflow :D
     * @param s
     * @return
     */
    public static String encodeURIComponent(String s)
    {
        String result = null;

        try
        {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e)
        {
            result = s;
        }

        return result;
    }

    public static void main(String[] args) {
		// Server
        Server server = new Server();
        server.config();
        server.run();
    }

}
