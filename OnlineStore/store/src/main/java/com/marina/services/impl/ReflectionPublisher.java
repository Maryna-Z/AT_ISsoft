package com.marina.services.impl;

import com.marina.constants.Constants;
import com.marina.domain.CategoryObj;
import com.marina.domain.ProductObj;
import com.marina.entities.Category;
import com.marina.exception.CategoryInitializationException;
import com.marina.services.ProductPublisher;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class ReflectionPublisher extends BasicProductPublisher implements ProductPublisher {
    @Override
    public List<CategoryObj> getCategories() {
        List<CategoryObj> categories = getCategoriesViaReflection();
        createProductsForCategories(categories);
        return categories;
    }

    private void createProductsForCategories(List<CategoryObj> categories) {
        AtomicReference<Integer> fakeProductId = new AtomicReference<>(1);
        categories.forEach(category -> {
            List<ProductObj> products = new ArrayList<>();
            for (int i = 0; i < Constants.PRODUCT_COUNT_IN_CATEGORY; i++) {
                String productName = populateProductNameByCategoryName(category.getCategoryName());
                ProductObj product = ProductObj.builder()
                        .productId(fakeProductId.get())
                        .name(productName)
                        .price(Double.valueOf(faker.commerce().price()))
                        .quantity(faker.number().numberBetween(0, 30))
                        .rating(faker.number().numberBetween(0, 11))
                        .build();
                fakeProductId.getAndSet(fakeProductId.get() + 1);
                products.add(product);
            }
            category.setProductObjList(products);
        });
    }


    private List<CategoryObj> getCategoriesViaReflection() {
        List<CategoryObj> categories= new ArrayList<>();
        Reflections reflections = new Reflections("com.marina.domain.reflection");
        Set<Class<? extends CategoryObj>> subClassesForCategory = reflections.getSubTypesOf(CategoryObj.class);
        for (Class clazz : subClassesForCategory) {
            CategoryObj categoryObj;
            try {
                categoryObj = (CategoryObj)Class.forName(clazz.getName()).newInstance();
            } catch (Exception ex) {
                throw new CategoryInitializationException("Error to execute getCategoriesViaReflection method", ex);
            }
            categories.add(categoryObj);
        }
        return categories;
    }
}
