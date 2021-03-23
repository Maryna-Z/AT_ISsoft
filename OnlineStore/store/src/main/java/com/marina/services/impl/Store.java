package com.marina.services.impl;
import com.marina.entities.Product;
import com.marina.services.ProductPublisher;
import com.marina.utility.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static com.marina.constants.Constants.DB_PUBLISHER_TYPE;

@Data
public class Store {
    ProductPublisher productPublisher;
    List<Product> products;
    ReentrantLock locker;
    Condition condition;
    List<Product> purchasesList;

    public  Store(){
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public Store(ArrayList<Product> products){
        this.products = products;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public List<Product> getProducts(int quantity){
        locker.lock();
        List<Product> purchasedProducts = new ArrayList<>();
        try {
            while (products.size() < quantity)
                condition.await();
            purchasedProducts = products.stream().limit(quantity).collect(Collectors.toList());
            products.removeAll(purchasedProducts);
            condition.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            locker.unlock();
        }
        return purchasedProducts;
    }

    public ProductPublisher getPublisher(String type){
        if (DB_PUBLISHER_TYPE.equalsIgnoreCase(type)){
            return new DBProductPublisher();
        }
        return new ReflectionPublisher();
    }

    public synchronized void createOrder(List<Product> availableProducts,
                                         List<Product> bothProduct,
                                         int selectedProductId){
        int processingTime = Utils.generateRandomValue(1, 30);
        availableProducts.stream()
                .filter(p -> (p.getProductID() == selectedProductId))
                .findFirst()
                .ifPresent(product -> new Thread(()->OrderProcessor.processOrder(
                        product, availableProducts, bothProduct, processingTime
                )).start());

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
