package com.marina.services.impl;

import com.marina.Product;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CleaningPurchases implements Runnable {
    List<Product> purchases;
    ReentrantLock locker;
    Condition condition;

    public CleaningPurchases(List<Product> purchases){
        this.purchases = purchases;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void deleteProducts(){
        locker.lock();
        purchases.clear();
        locker.unlock();
    }

    public List<Product> getPurchases(){
        return purchases;
    }

    @Override
    public void run() {
        deleteProducts();
    }
}
