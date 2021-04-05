package com.marina.services.http;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

import static com.marina.constants.Constants.KEY;

public class Auth extends Authenticator {

    public Result authenticate(HttpExchange httpExchange) {
        String authorization = httpExchange.getRequestHeaders()
                .get("Authorization")
                .stream().findFirst()
                .orElse(null);

        authorization = authorization.replace("Bear:", StringUtils.EMPTY).trim();
        authorization = new String(Base64.getDecoder().decode(authorization));

        if (KEY.equals(authorization)){
            return new Success(new HttpPrincipal("c0nst", "realm"));
        } else if (authorization.equals(null)){
            return new Failure(403);
        } else {
            return new Retry(401);
        }
    }
}
