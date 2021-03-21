package com.marina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    public int categoryID;
    public String categoryName;

    public Category(String categoryName) {
        this.categoryName =  categoryName;
    }
}