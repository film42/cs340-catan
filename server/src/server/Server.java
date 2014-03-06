package server;

import static spark.Spark.*;

import route.MoveRoute;
import route.assets.StaticRoute;
import route.game.*;
import route.games.CreateRoute;
import route.games.JoinRoute;
import route.games.ListRoute;
import route.user.LoginRoute;
import route.user.RegisterRoute;

public class Server {

    public Server() {

    }

    private void run() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.config();
        server.run();
    }

    private void config() {
        setPort(8081);

        // Each Route
        new LoginRoute().attach();
        new RegisterRoute().attach();
        new ListRoute().attach();
        new JoinRoute().attach();
        new CreateRoute().attach();
        new ResetRoute().attach();
        new ModelRoute().attach();
        new ListAIRoute().attach();
        new addAIRoute().attach();
        new CommandsRoute().attach();
        new MoveRoute().attach();
        // Statics do this last
        new StaticRoute().attach();
    }

}
