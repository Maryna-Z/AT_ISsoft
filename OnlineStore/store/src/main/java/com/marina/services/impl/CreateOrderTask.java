package com.marina.services.impl;

import com.marina.Product;

import java.util.List;
import java.util.concurrent.Callable;

public class CreateOrderTask implements Callable {
    Store store;
    int quantity;

    public CreateOrderTask(Store store, int quantity){
        this.store = store;
        this.quantity = quantity;
    }

    @Override
    public List<Product> call() throws Exception {
        List<Product> purchases = store.getProducts(quantity);
        return purchases;
    }
}
