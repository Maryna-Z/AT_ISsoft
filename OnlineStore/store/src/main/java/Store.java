import product.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Store {

    List<Product> products;
    RandomStorePopulator randomStorePopulator;
    ReentrantLock locker;
    Condition condition;
    int capacity;

    public  Store(){
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public Store(ArrayList<Product> products, int capacity){
        this.products = products;
        locker = new ReentrantLock();
        condition = locker.newCondition();
        this.capacity = capacity;
    }

    public Store(int capacity) {
        this.capacity = capacity;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public List<Product> newSortProduct(String fieldName, String sortOrder){
        List<Product> sortedList =  new ArrayList<>();
        switch (fieldName.toLowerCase()) {
            case "name":
                if (sortOrder.toLowerCase().equals("asc")) {
                    sortedList = products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
                } else if (sortOrder.toLowerCase().equals("desc")) {
                    Comparator<Product> productNameComparatorDesc = Comparator.comparing(Product::getName, (s1, s2) -> {
                        return s2.compareTo(s1);
                    });
                    sortedList = products.stream().sorted(productNameComparatorDesc).collect(Collectors.toList());
                }
                break;
            case "price":
                if (sortOrder.toLowerCase().equals("asc")) {
                    sortedList = products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
                } else if (sortOrder.toLowerCase().equals("desc")) {
                    Comparator<Product> productPriceComparatorDesc = Comparator.comparing(Product::getPrice, (s1, s2) -> {
                        return s2.compareTo(s1);
                    });
                    sortedList = products.stream().sorted(productPriceComparatorDesc).collect(Collectors.toList());
                }
                break;
            case "rating":
                if (sortOrder.toLowerCase().equals("asc")) {
                    sortedList = products.stream().sorted(Comparator.comparing(Product::getRating)).collect(Collectors.toList());
                } else if (sortOrder.toLowerCase().equals("desc")) {
                    Comparator<Product> productRatingComparatorDesc = Comparator.comparing(Product::getRating, (s1, s2) -> {
                        return s2.compareTo(s1);
                    });
                    sortedList = products.stream().sorted(productRatingComparatorDesc).collect(Collectors.toList());
                }
                break;
        }
        return sortedList;
    }

    public List<Product> getProductsByCategoryID(int categoryID){
        List<Product> productsByCategory = products.stream().filter(p -> categoryID == p.getCategoryID())
                .collect(Collectors.toList());
        return productsByCategory;
    }

    /*while in store more then capacity products thread waits*/
    public List<Product> putProducts(int quantity){
        locker.lock();
        try {
            while (products.size() > capacity)
                condition.await();
            randomStorePopulator = new RandomStorePopulator();
            products = randomStorePopulator.populateStoreFromDBCategory(quantity);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
        return products;
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
}
