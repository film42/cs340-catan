package server;

import static spark.Spark.*;

import route.assets.StaticRoute;
import route.game.ListRoute;
import route.user.LoginRoute;
import route.user.RegisterRoute;

public class Server {

    public Server() {

    }

    private void config() {
        setPort(8081);

        // Each Route
        new LoginRoute().attach();
        new RegisterRoute().attach();
        new ListRoute().attach();

        // Statics (Last)
        new StaticRoute().attach();
    }

    private void run() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.config();
        server.run();
    }

}
