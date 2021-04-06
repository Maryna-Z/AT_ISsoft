package com.marina.services.http;

import com.marina.exception.CommonException;
import com.marina.utility.Utils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class CommonHandler {
    protected void createResponse(HttpExchange exchange, int responseStatus, Object object) {
        AtomicReference<String> json = new AtomicReference<>("");

        Optional.ofNullable(object)
                .ifPresent(o -> {
                    json.set(Utils.convertToJSON(object));
                });
        try {
            exchange.sendResponseHeaders(
                    responseStatus,
                    json.get().length()
            );

            OutputStream outputStream = exchange.getResponseBody();

            outputStream.write(json.get().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            throw new  CommonException("Error to generate response", ex);
        }
    }
}