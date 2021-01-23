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
        List<Product> products = populator.populateStore(90);
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

        CreateThreadFunctionality threadFunctionality = new CreateThreadFunctionality(store);
        threadFunctionality.createOrders();
        threadFunctionality.cleanBasket();
    }

}
