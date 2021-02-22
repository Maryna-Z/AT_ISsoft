package com.marina;

import com.github.javafaker.Faker;
import com.marina.dao.builder.QueryBuilder;
import com.marina.dao.commonDAO.products.CategoriesDAO;


import java.util.ArrayList;
import java.util.List;

public class RandomStoreFilling {

    Faker faker = new Faker();

    public List<Category> categories = new ArrayList<>();
    CategoriesDAO categoriesDAO = new CategoriesDAO();


    public List<Category> getAllCategoriesFromDB(){
        categories = categoriesDAO.loadCategories(QueryBuilder.getAllCategories());
        return categories;
    }

    public List<Product> populateStoreFromDBCategory(int quantity){
        List<Product> products = new ArrayList<>();
        String productName = "";
        String categoryName = "";
        Category category = null;
        int categoryID = 0;
        int categoriesLength = getAllCategoriesFromDB().size();
        int min = categories.get(1).getCategoryID();
        int max = categories.get(categoriesLength-1).getCategoryID();
        Product product = null;
        for (int i = 0; i < quantity; i++) {
            int randomInt = (int) (Math.random() * categoriesLength);
            category = getAllCategoriesFromDB().get(randomInt);
            categoryName = category.getName();
            categoryID = category.getCategoryID();
            productName = populateProductNameByCategoryName(categoryName);
            product = new Product.Builder()
                    .withName(productName)
                    .withCategory(categoryID)
                    .withPrice(Double.valueOf(faker.commerce().price()))
                    .withQuantity(faker.number().numberBetween(0, 30))
                    .withRating(faker.number().numberBetween(0, 11))
                    .build();
            products.add(product);
        }
        return products;
    }

    public String populateProductNameByCategoryName(String categoryName){
        categoryName = categoryName.trim().toLowerCase();
        String productName = "";
        if (categoryName.equals("fruit")){
            productName = faker.food().fruit();
        } else if (categoryName.equals("spice")){
            productName = faker.food().spice();
        } else if (categoryName.equals("vegetable")){
            productName = faker.food().vegetable();
        }
        return productName;
    }
}
