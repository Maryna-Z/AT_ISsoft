package com.marina.domain;

import com.marina.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryObj {
    public String categoryName;
    public List<ProductObj> productObjList;

    public CategoryObj(String categoryName) {
        this.categoryName =  categoryName;
    }

    public CategoryObj(Category category, List<ProductObj> productObjList){
        this.categoryName = category.getCategoryName();
        this.productObjList = productObjList;
    }
}
