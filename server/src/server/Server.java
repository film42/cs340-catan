package server;

import static spark.Spark.*;

import route.user.LoginRoute;

public class Server {

    public Server() {

    }

    private void config() {
        setPort(8081);

        LoginRoute lR = new LoginRoute();
        lR.attach();
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
