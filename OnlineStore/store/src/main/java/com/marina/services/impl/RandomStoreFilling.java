package com.marina.services.impl;

import com.github.javafaker.Faker;
import com.marina.Category;
import org.reflections.Reflections;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomStoreFilling {

    private Faker faker = new Faker();

    public List<String> getCategoriesNameViaReflection() throws NoSuchFieldException {
        List<String> categoryNames = new ArrayList<>();
        Reflections reflections = new Reflections("categories");
        Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
        for (int i = 0; i < subClassesForCategory.size(); i++){
            categoryNames.add(
                    subClassesForCategory.parallelStream()
                    .collect(Collectors.toList())
                    .get(i)
                    .getName()
            );
        }
        return categoryNames;
    }



}
