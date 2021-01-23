import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CreateThreadFunctionality {

    Store store;
    List<Product> purcheses = new ArrayList<>();

    CreateThreadFunctionality(Store store){
        this.store = store;
    }

    public void createOrders(){
        System.out.println("purchases:");
        for (int i = 1; i < 4; i++){// 3 is quantity of consumers
            ExecutorService executor = Executors.newSingleThreadExecutor();
            //5 is quantity of products, which buy one Consumer
            int randomInt = (int) (Math.random() * 30);
            Callable<List<Product>> createOrderTask = new CreateOrderTask(store, randomInt);
            Future future = executor.submit(createOrderTask);
            try {
                purcheses = (List<Product>)future.get(3, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            executor.shutdown();
            System.out.println(purcheses);
        }
    }

    public void cleanBasket(){
        ScheduledExecutorService cleanBySchedule = Executors.newSingleThreadScheduledExecutor();
        Runnable cleanUp = new CleanUpPurchases(purcheses);
        cleanBySchedule.scheduleAtFixedRate(cleanUp, 2, 2, TimeUnit.MINUTES);
        cleanBySchedule.shutdown();
    }

}
