package com.if23b212.mtcg;

import com.if23b212.mtcg.config.ServerConfig;
import com.if23b212.mtcg.rest.user.UserController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * Anmerkung: https://stackoverflow.com/questions/11655720/the-correct-way-to-pass-data-to-from-a-java-httphandler-class-for-java-httpserve
 */
public class MTCGApplication {

    private final static String SERVER_STARTING_MESSAGE = "Server is starting....";
    private final static String SERVER_RUNNING_MESSAGE = "Server is running....";
    public static void main(String[] args) {

        try {
            startServer();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startServer() throws IOException {
        System.out.println(SERVER_STARTING_MESSAGE);

        HttpServer server = HttpServer.create(new InetSocketAddress(ServerConfig.INET_SOCKET_PORT), 0);
        configureEndpoints(server);
        server.start();

        System.out.println(SERVER_RUNNING_MESSAGE);
    }

    private static void configureEndpoints(HttpServer server) {

        if(server != null) {
            server.createContext("/users", new UserController.UserRegistration());
            server.createContext("/sessions", new UserController.UserLogin());
        }
    }
}
