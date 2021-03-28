package com.marina.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Integer productId;
    private String name;
    private int rating;
    private double price;
    private Integer categoryId;
    private int quantity;

    public Product(String name, int rating, double price, Integer categoryId, int quantity){
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }
}
