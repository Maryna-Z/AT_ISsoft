package com.marina.services.http;

import com.marina.constants.Constants;
import com.marina.exception.CommonException;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {

    public void createServer(String context) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);

            switch (context) {
                case Constants.CATEGORIES_CONTEXT_URL:
                    server.createContext(context, new CategoriesHandler());
                    break;
                case Constants.CARTS_CONTEXT_URL:
                    server.createContext(context, new ProductCartHandler());
                    break;
            }

            server.setExecutor(null);
            server.start();
            System.out.println("Server started");
        } catch (Exception ex) {
            throw new CommonException("Error to build server", ex);
        }
    }
}