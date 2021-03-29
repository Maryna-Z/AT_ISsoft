package com.marina.services.impl;

import com.marina.builders.QueryBuilder;
import com.marina.constants.Constants;
import com.marina.dao.CategoriesDAO;
import com.marina.dao.ProductsDAO;
import com.marina.domain.CategoryObj;
import com.marina.domain.ProductObj;
import com.marina.entities.Category;
import com.marina.entities.Product;
import com.marina.services.ProductPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBProductPublisher extends BasicProductPublisher implements ProductPublisher {

    @Override
    public List<CategoryObj> getCategories() {
        List<Category> categoriesFromDB = getAllCategoriesFromDB();
        createProductsForCategories(categoriesFromDB);
        List<Product> products = getAllProducts();
        return convert(categoriesFromDB, products);
    }

    private List<Category> getAllCategoriesFromDB() {
        return new CategoriesDAO().load(QueryBuilder.getAllCategories());
    }

    private List<Product> getAllProducts() {
        return new ProductsDAO().load(QueryBuilder.getAllProducts());
    }

    private void createProductsForCategories(List<Category> categories) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < Constants.PRODUCT_COUNT_IN_CATEGORY; i++) {
            categories.forEach(category -> {
                String productName = populateProductNameByCategoryName(category.getCategoryName());
                Product product = Product.builder()
                        .name(productName)
                        .categoryId(category.getCategoryId())
                        .price(Double.valueOf(faker.commerce().price()))
                        .quantity(faker.number().numberBetween(0, 30))
                        .rating(faker.number().numberBetween(0, 11))
                        .build();
                products.add(product);
            });
        }
        new ProductsDAO().insert(QueryBuilder.insertProductQuery(), products);
    }

    private List<CategoryObj> convert(List<Category> categories, List<Product> products) {
        List<CategoryObj> list = new ArrayList<>();
        categories.forEach(category -> {
            List<ProductObj> matchedProducts = products.stream()
                    .filter(product -> product.getCategoryId().equals(category.categoryId))
                    .map(ProductObj::new)
                    .collect(Collectors.toList());

            list.add(
                    new CategoryObj(category, matchedProducts)
            );
        });
        return list;
    }
}
