package server;

import com.google.inject.Injector;
import comm.request.CreateGameRequest;
import comm.request.JoinGameRequest;
import comm.request.UserRequest;
import model.InjectorFactory;
import model.JsonImpl;
import model.Model;
import model.ModelModule;
import model.facade.GameFacade;
import model.facade.GamesFacade;
import model.facade.MoveFacade;
import model.facade.UtilFacade;
import modelInterfaces.base.GameInfo;
import modelInterfaces.users.User;
import persistence.PersistenceManager;
import route.MoveRoute;
import route.game.*;
import route.games.CreateRoute;
import route.games.JoinRoute;
import route.games.ListRoute;
import route.user.LoginRoute;
import route.user.RegisterRoute;
import route.util.ChangeLogLevelRoute;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    private void config(int saveInterval, String pluginName) {
		Server.log.info("Configuring server...");

        // Set port here
        setPort(8081);

        // Set static directory to "gameplay"
        externalStaticFileLocation("../gameplay");

		// Google Guice injection
		Injector injector = InjectorFactory.createInjector(new ModelModule());

        // Facade Classes
		Model myGame = injector.getInstance(Model.class);

        PersistenceManager myPersistenceManager = new PersistenceManager(saveInterval);

		if (myPersistenceManager.loadPlugin(pluginName) == null) {
			Server.log.warning("No persistence provider was provided.  Persistent state won't persist.");
			return;
		}
//        myPersistenceManager.clearDatabase();

        Set<User> loadedUsers = new HashSet<>();
        List<User> users = myPersistenceManager.loadUsers();
        loadedUsers.addAll(users);
        myGame.setUsers(loadedUsers);

        List<GameInfo> loadedGames = myPersistenceManager.loadGames();
        myGame.setGames(loadedGames);

        UtilFacade myUtilFacade = new UtilFacade(myGame, myPersistenceManager);
        GamesFacade myGamesFacade = new GamesFacade(myGame, myPersistenceManager);
        GameFacade myGameFacade = new GameFacade(myGame);
        MoveFacade myMoveFacade = new MoveFacade(myGame, myPersistenceManager);


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

        if(loadedUsers.size() < 1){
            initDefaultUsers(myUtilFacade);
        }
        if(loadedGames.size() < 1) {
            initDefaultGames(myGamesFacade, myGame);
        }

    }

    private void initDefaultUsers(UtilFacade myUtilFacade) {
        myUtilFacade.onUserRegister(new UserRequest("Adam", "adam"));
        myUtilFacade.onUserRegister(new UserRequest("Steve", "steve"));
        myUtilFacade.onUserRegister(new UserRequest("June", "june"));
        myUtilFacade.onUserRegister(new UserRequest("Garrett", "garrett"));
    }

    private void initDefaultGames(GamesFacade myGamesFacade, Model myGame) {
        List<GameInfo> games = myGame.getGames();
        myGamesFacade.onCreateGame(new CreateGameRequest(true, true, true, "Just Started"));
        int id = games.get(games.size() - 1).getId();
        myGamesFacade.onJoinGame(new JoinGameRequest("red", ""+id), "Adam");
        myGamesFacade.onJoinGame(new JoinGameRequest("blue", ""+id), "Garrett");
        myGamesFacade.onJoinGame(new JoinGameRequest("orange", ""+id), "June");
        myGamesFacade.onJoinGame(new JoinGameRequest("green",""+id), "Steve");
        id = games.get(games.size() - 1).getId();
        myGamesFacade.onCreateGame(new CreateGameRequest(true, true, true, "Test Game"));
        myGamesFacade.onJoinGame(new JoinGameRequest("red", ""+id), "Adam");
        myGamesFacade.onJoinGame(new JoinGameRequest("blue", ""+id), "Garrett");
        id = games.get(games.size() - 1).getId();
        // Past Setup
        myGamesFacade.onCreateGame(new CreateGameRequest(true, true, true, "Past Setup"));
        myGamesFacade.onJoinGame(new JoinGameRequest("red", ""+id), "Adam");
        myGamesFacade.onJoinGame(new JoinGameRequest("blue", ""+id), "June");
        id = games.get(games.size() - 1).getId();
        // Half way
        myGamesFacade.onCreateGame(new CreateGameRequest(true, true, true, "Half Way"));
        myGamesFacade.onJoinGame(new JoinGameRequest("red", ""+id), "Adam");
        myGamesFacade.onJoinGame(new JoinGameRequest("blue", ""+id), "Garrett");
        myGamesFacade.onJoinGame(new JoinGameRequest("orange", ""+id), "June");
        myGamesFacade.onJoinGame(new JoinGameRequest("green", ""+id), "Steve");

        // now load an existing model from a json and put it in our Past Setup game
//        try {
//            String gameJson = new Scanner(new File("PastSetupGame.json")).useDelimiter("\\Z").next();
//            Game game = JsonImpl.fromJson(gameJson, GameImpl.class);
//            myGame.getGames().get(2).setData(game);
//
//            gameJson = new Scanner(new File("HalfWayGame.json")).useDelimiter("\\Z").next();
//            game = JsonImpl.fromJson(gameJson, GameImpl.class);
//            myGame.getGames().get(3).setData(game);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        List<Player> players = myGame.findGameById(1).getData().getPlayers();
//        for (Player player : players) { //give them lots of resources
//            Resources money = injector.getInstance(Resources.class);
//            money.setResources(9, 9, 9, 9, 9);
//            player.setResources(money);
//        }
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

    private static final int DEFAULT_INTERVAL = 10;

    public static void main(String[] args) {
        // Server
        Server server = new Server();
        int saveInterval = DEFAULT_INTERVAL; //default save Interval
        String pluginName = "";
        try{
        if (args.length > 1) {
            saveInterval = Integer.parseInt(args[0]);
            pluginName = args[1];
        }
        if (saveInterval < 1) {
            throw new IllegalArgumentException();
        }
        }catch (IllegalArgumentException e){
            log.warning("Bad save Interval argument: " + args[0]);
            saveInterval = DEFAULT_INTERVAL;
        }
        server.config(saveInterval, pluginName);
        server.run();
    }

}
