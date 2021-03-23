package com.marina;

import com.marina.entities.Product;
import com.marina.scheduler.MyScheduler;
import com.marina.scheduler.tasks.ClearBothProduct;
import com.marina.services.ProductPublisher;
import com.marina.services.impl.Store;
import com.marina.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.marina.constants.Constants.CLEAR_BOTH_PRODUCT_TIME;

public class StoreApp {

    public static void main(String[] args) {

        //populate Store
        String publisherType = args[0];
        ProductPublisher publisher = new Store().getPublisher(publisherType);
        List<Product> products = publisher.getProducts();
        List<Product> bothProducts = new ArrayList<>();
        int selectedProductId = Utils.getIntFromConsole(System.in);
        new Store().createOrder(products, bothProducts, selectedProductId);
        int selectedProductId2 = Utils.getIntFromConsole(System.in);
        new Store().createOrder(products, bothProducts, selectedProductId2);
        new MyScheduler().execute(
                new ClearBothProduct(bothProducts),
                CLEAR_BOTH_PRODUCT_TIME
        );





        /*
        URL resource = com.marina.StoreApp.class.getResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());

        List<Comparator<Product>> comparatorList = new ListProductComparators(sortCondition).getComparatorList(sortCondition);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }*/
    }

}
