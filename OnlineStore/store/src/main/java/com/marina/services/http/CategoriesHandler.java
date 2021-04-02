package com.marina.services.http;

import com.marina.domain.CategoryObj;
import com.marina.services.Wrapper;
import com.marina.services.impl.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.List;

public class CategoriesHandler extends CommonHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange){
        if ("GET".equals(httpExchange.getRequestMethod())) {
            List<CategoryObj> categoriesList = Store.getInstance(null).getCategoryObjList();
            createResponse(httpExchange, 200, categoriesList); // TODO receive wrapper (not categoryList)
        } else {
            createResponse(httpExchange, 405, null);
        }
    }
}
