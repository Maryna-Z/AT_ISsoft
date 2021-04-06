package com.marina.services.impl;

import com.marina.domain.CategoryObj;
import com.marina.services.ProductPublisher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.marina.constants.Constants.DB_PUBLISHER_TYPE;

@Data
public class Store {
    private static Store instance;
    List<CategoryObj> categoryObjList;

    private Store(String type) {
        this.categoryObjList = publicCategories(type);
    }

    public synchronized static Store getInstance(String type) {
        if (instance == null) {
            instance = new Store(type);
        }
        return instance;
    }

    private List<CategoryObj> publicCategories(String type) {
        return getPublisher(type).getCategories();
    }

    private ProductPublisher getPublisher(String type) {
        if (DB_PUBLISHER_TYPE.equalsIgnoreCase(type)) {
            return new DBProductPublisher();
        }
        return new ReflectionPublisher();
    }
}
