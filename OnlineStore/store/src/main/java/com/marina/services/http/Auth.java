package com.marina.services.http;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;
import java.util.List;

import static com.marina.constants.Constants.KEY;

public class Auth extends Authenticator {

    public Result authenticate(HttpExchange httpExchange) {
        List<String> authorizationHeaders = httpExchange.getRequestHeaders().get("Authorization");

        if (authorizationHeaders == null){
            return new Failure(401);
        }

        String authorization = authorizationHeaders.stream()
                .findFirst()
                .orElse(null);

        authorization = authorization.replace("Bear:", StringUtils.EMPTY).trim();
        try {
            authorization = new String(Base64.getDecoder().decode(authorization));
        } catch (Exception ex) {
            return new Failure(500);
        }

        if (KEY.equals(authorization)) {
            return new Success(new HttpPrincipal("c0nst", "realm"));
        }
        return new Failure(401);
    }
}
