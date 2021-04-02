package com.marina.services;

import com.marina.domain.CategoryObj;

import java.util.List;

public class Wrapper {
    List<CategoryObj> categories;

    public Wrapper(List<CategoryObj> categories){
        this.categories = categories;
    }
}
