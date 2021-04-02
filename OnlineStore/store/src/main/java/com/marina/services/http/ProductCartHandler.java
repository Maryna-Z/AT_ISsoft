package com.marina.services.http;

import com.marina.domain.ProductObj;
import com.marina.dto.ProductRequestObject;
import com.marina.services.impl.OrderBuilder;
import com.marina.services.impl.Store;
import com.marina.utility.Utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

import static com.marina.constants.Constants.UTF_8;

public class ProductCartHandler extends CommonHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (checkToken(httpExchange)) {
            if ("POST".equals(httpExchange.getRequestMethod())) {
                String stringBody = Utils.getStringBody(httpExchange.getRequestBody());
                ProductRequestObject productRequestObject = Utils.convertFromJSON(
                        stringBody,
                        ProductRequestObject.class
                );

                if (productRequestObject.getId() < 1) {
                    createResponse(httpExchange, 400, null);
                } else {
                    OrderBuilder.getInstance(null)
                            .createOrder(productRequestObject.getId());
                    createResponse(httpExchange, 201, null);
                }
            } else {
                createResponse(httpExchange, 405, null);
            }
        } else {
            createResponse(httpExchange, 401, null);
        }
    }
}
