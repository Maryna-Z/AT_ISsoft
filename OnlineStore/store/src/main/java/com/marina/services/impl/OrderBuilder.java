package com.marina.services.impl;

import com.marina.domain.CategoryObj;
import com.marina.domain.ProductObj;
import com.marina.scheduler.tasks.ClearBothProduct;
import com.marina.utility.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.marina.constants.Constants.CLEAR_BOTH_PRODUCT_TIME;

public class OrderBuilder {
    private List<ProductObj> availableProducts;
    private List<ProductObj> perchesProduct = new ArrayList<>();

    public OrderBuilder(List<CategoryObj> categories){
        this.availableProducts = categories.stream()
                .map(categoryObj -> categoryObj.getProductObjList())
                .flatMap(productObjs -> productObjs.stream())
                .collect(Collectors.toList());
    }

    public synchronized void createOrder(Integer productId){
        availableProducts.stream()
                .filter(productObj -> productObj.getProductId().equals(productId))
                .findFirst()
                .ifPresent(productObj -> new Thread(() -> OrderProcessor.createOrder(
                        productObj,
                        availableProducts,
                        perchesProduct
                        )).start()
                );
    }

    public void clearPerchesProduct(){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(
                new ClearBothProduct(perchesProduct),
                CLEAR_BOTH_PRODUCT_TIME,
                TimeUnit.MINUTES
        );
    }

    private static class OrderProcessor{
        public static void createOrder(ProductObj product,
                                       List<ProductObj> availableProducts,
                                       List<ProductObj> perchesProduct){
            int processingTime = Utils.generateRandomValue(1, 30) * 1000;

            try {
                availableProducts.remove(product);
                Thread.sleep(processingTime);
                availableProducts.add(product);
                perchesProduct.add(product);
            } catch (InterruptedException ex) {
                System.out.println("Error to create order for product: " + product.getProductId() + " " + ex.getMessage());
            }
        }
    }
}
