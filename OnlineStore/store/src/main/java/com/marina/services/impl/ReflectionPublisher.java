package com.marina.services.impl;

import com.marina.entities.Category;
import com.marina.entities.Product;
import com.marina.services.ProductPublisher;
import org.reflections.Reflections;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReflectionPublisher extends BasicProductPublisher implements ProductPublisher {

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try {
            List<Category> categoriesNameViaReflection = getCategoriesNameViaReflection();
            products = publicProducts(categoriesNameViaReflection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }


    public List<Category> getCategoriesNameViaReflection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        List<Category> categories= new ArrayList<>();
        Reflections reflections = new Reflections("com.marina.categories");
        Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
        for (Class clazz : subClassesForCategory) {
            Object o = Class.forName(clazz.getName()).newInstance();
            int categoryID = clazz.getField("categoryID").getInt(o);
            String categoryName = (String) clazz.getField("categoryName").get(o);
            categories.add(new Category(categoryID, categoryName));
        }
        return categories;
    }
}
