package com.marina.domain;

import com.marina.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductObj {
    private Integer productId;
    private String name;
    private int rating;
    private double price;
    private int quantity;

    public ProductObj(Product product){
        this.productId = product.getProductId();
        this.name = product.getName();
        this.rating = product.getRating();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
