package com.marina.services.http;

import com.marina.exception.CommonException;
import com.marina.utility.Utils;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.marina.constants.Constants.KEY;

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

    protected boolean checkToken(HttpExchange httpExchange){
        List<String> authorizationList = httpExchange.getRequestHeaders()
                .get("Authorization");
        if (authorizationList == null){
            return false;
        }
        String authorization = authorizationList.stream().findFirst().orElse(null);

        if(StringUtils.isBlank(authorization)){
            return false;
        }
        authorization = authorization.replace("Bear:", StringUtils.EMPTY).trim();
        authorization = new String(Base64.getDecoder().decode(authorization));
        return KEY.equals(authorization);
    }
}