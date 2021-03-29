package com.marina.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    public Integer categoryId;
    public String categoryName;

    public Category(String categoryName) {
        this.categoryName =  categoryName;
    }
}