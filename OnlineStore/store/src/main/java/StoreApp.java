import product.ChainComparator;
import product.ListProductComparators;
import product.Product;
import utility.DOMExecuter;


import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

public class StoreApp {
    public static void main(String[] args) {
        /**/

        Store store = new Store(3000);
        RandomStorePopulator populator = new RandomStorePopulator();
        List<Product> products = populator.populateStore(30);
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
        store.setProducts(products);

        URL resource = StoreApp.class.getResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());

        List<Comparator<Product>> comparatorList = new ListProductComparators(sortCondition).getComparatorList(sortCondition);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }

        List<Product> purcheses = new ArrayList<>();
        System.out.println("purchases:");
        for (int i = 1; i < 4; i++){// 3 is quantity of consumers
            ExecutorService executor = Executors.newSingleThreadExecutor();
            //5 is quantity of products, which buy one Consumer
            Callable<List<Product>> createOrderTask = new CreateOrderTask(store, 5);
            Future future = executor.submit(createOrderTask);
            try {
                purcheses = (List<Product>)future.get(3, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            executor.shutdown();
            System.out.println(purcheses);
        }

        ScheduledExecutorService cleanBySchedule = Executors.newSingleThreadScheduledExecutor();
        Runnable cleanUp = new CleanUpPurchases(purcheses);
        cleanBySchedule.scheduleAtFixedRate(cleanUp, 2, 2, TimeUnit.MINUTES);
        cleanBySchedule.shutdown();
        //purcheses = ((CleanUpPurchases) cleanUp).getPurchases();

    }

}
