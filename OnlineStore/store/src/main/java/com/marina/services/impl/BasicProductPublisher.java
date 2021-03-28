package com.marina.services.impl;

import com.github.javafaker.Faker;

public class BasicProductPublisher<T> {
    Faker faker = new Faker();

    String populateProductNameByCategoryName(String categoryName) {
        categoryName = categoryName.trim().toLowerCase();
        String productName = "";
        switch (categoryName){
            case "fruit":
                productName = faker.food().fruit();
                break;
            case "spice":
                productName = faker.food().spice();
                break;
            case "vegetable":
                productName = faker.food().vegetable();
                break;
        }
        return productName;
    }
}
