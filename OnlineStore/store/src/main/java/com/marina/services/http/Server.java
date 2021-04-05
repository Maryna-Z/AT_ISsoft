package com.marina.services.http;

import com.marina.constants.Constants;
import com.marina.exception.CommonException;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

import static com.marina.constants.Constants.CARTS_CONTEXT_URL;
import static com.marina.constants.Constants.CATEGORIES_CONTEXT_URL;

public class Server {

    public void createServer() {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);

            server.createContext(CATEGORIES_CONTEXT_URL, new CategoriesHandler())
                    .setAuthenticator(new Auth());

            server.createContext(CARTS_CONTEXT_URL, new ProductCartHandler())
                    .setAuthenticator(new Auth());

            server.setExecutor(null);
            server.start();
            System.out.println("Server started");
        } catch (Exception ex) {
            throw new CommonException("Error to build server", ex);
        }
    }
}