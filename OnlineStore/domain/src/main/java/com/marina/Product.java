package com.marina;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int productID;
    private String name;
    private int rating;
    private double price;
    private int categoryID;
    private int quantity;

    public Product(String name, int rating, double price, int categoryID, int quantity){
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.categoryID = categoryID;
        this.quantity = quantity;
    }

    public Product(Product product) {
        this.productID = product.productID;
        this.name = product.name;
        this.rating = product.rating;
        this.price = product.price;
        this.categoryID = product.categoryID;
        this.quantity = product.quantity;
    }
}
