package com.marina.builders;

public class QueryBuilder {
    public static String getAllCategories(){
        return "SELECT * FROM category";
    }

    public static String getAllProducts() { return "SELECT * FROM product"; }

    public static  String deleteAllCategories(){
        return "DELETE FROM category";
    }

    public static String insertProductQuery(){
        return "INSERT INTO product (name, rating, price, categoryId, quantity) VALUES(" +
                "?, ?, ?, ?, ?)";
    }
}
