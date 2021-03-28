package com.marina.services.impl;

import com.marina.domain.CategoryObj;
import com.marina.services.ProductPublisher;
import lombok.Data;

import java.util.List;

import static com.marina.constants.Constants.DB_PUBLISHER_TYPE;

@Data
public class Store {
    List<CategoryObj> categoryObjList;

    public Store(String type){
        this.categoryObjList = publicCategories(type);
    }

    private List<CategoryObj> publicCategories(String type){
        return getPublisher(type).getCategories();
    }

    private ProductPublisher getPublisher(String type){
        if (DB_PUBLISHER_TYPE.equalsIgnoreCase(type)){
            return new DBProductPublisher();
        }
        return new ReflectionPublisher();
    }
}
