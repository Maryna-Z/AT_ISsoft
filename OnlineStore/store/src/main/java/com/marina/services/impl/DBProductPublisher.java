package com.marina.services.impl;

import com.marina.entities.Category;
import com.marina.entities.Product;
import com.marina.dao.builder.QueryBuilder;
import com.marina.dao.commonDAO.CategoriesDAO;
import com.marina.services.ProductPublisher;
import java.util.List;

public class DBProductPublisher extends BasicProductPublisher implements ProductPublisher {


    @Override
    public List<Product> getProducts() {
        List<Category> allCategoriesFromDB = getAllCategoriesFromDB();
        return publicProducts(allCategoriesFromDB);
    }

    private List<Category> getAllCategoriesFromDB() {
        return new CategoriesDAO().loadCategories(QueryBuilder.getAllCategories());
    }
}
