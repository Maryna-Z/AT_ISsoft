package com.marina.services.impl;
import com.marina.entities.Product;
import com.marina.scheduler.tasks.ClearBothProduct;
import com.marina.services.ProductPublisher;
import com.marina.utility.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.marina.constants.Constants.CLEAR_BOTH_PRODUCT_TIME;
import static com.marina.constants.Constants.DB_PUBLISHER_TYPE;

@Data
@NoArgsConstructor
public class Store {
    List<Product> products;
    List<Product> bothProducts;

    public Store(List<Product> products) {
        this.products = products;
        bothProducts = new ArrayList<>();
    }

    public ProductPublisher getPublisher(String type){
        if (DB_PUBLISHER_TYPE.equalsIgnoreCase(type)){
            return new DBProductPublisher();
        }
        return new ReflectionPublisher();
    }

    public synchronized void createOrder(int selectedProductId){
        int processingTime = Utils.generateRandomValue(1, 30);
        products.stream()
                .filter(p -> (p.getProductID() == selectedProductId))
                .findFirst()
                .ifPresent(product -> new Thread(()->OrderProcessor.processOrder(
                        product, products, bothProducts, processingTime
                )).start());

    }

    public void clearBothProduct() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(
                new ClearBothProduct(bothProducts),
                CLEAR_BOTH_PRODUCT_TIME,
                TimeUnit.MINUTES
        );
        scheduledExecutorService.shutdown();
    }

    public static class OrderProcessor{

        public static void processOrder(Product product,
                                        List<Product> availableProducts,
                                        List<Product> bothProductList,
                                        int processingTime){
            try {
                availableProducts.remove(product);
                Thread.sleep(processingTime * 1000);
                bothProductList.add(product);
                availableProducts.add(product);
            } catch (InterruptedException ex) {
                System.out.println("Couldn't to seep thread");
            }
        }
    }

}
