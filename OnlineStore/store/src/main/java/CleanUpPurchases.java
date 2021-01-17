import product.Product;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CleanUpPurchases implements Runnable {
    List<Product> purchases;
    ReentrantLock locker;
    Condition condition;

    public CleanUpPurchases(List<Product> purchases){
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
