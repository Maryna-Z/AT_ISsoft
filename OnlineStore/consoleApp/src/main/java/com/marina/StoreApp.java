package com.marina;

import com.marina.entities.Product;
import com.marina.services.ProductPublisher;
import com.marina.services.impl.ChainComparator;
import com.marina.services.impl.ListProductComparators;
import com.marina.services.impl.Store;
import com.marina.utility.DOMExecuter;
import com.marina.utility.Utils;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StoreApp {

    public static void main(String[] args) {

        String publisherType = args[0];
        ProductPublisher publisher = new Store().getPublisher(publisherType);
        List<Product> products = publisher.getProducts();
        Store store = new Store(products);
        int selectedProductId1 = Utils.getIntFromConsole(System.in);
        store.createOrder(selectedProductId1);
        int selectedProductId2 = Utils.getIntFromConsole(System.in);
        store.createOrder(selectedProductId2);
        store.clearBothProduct();
        
        URL resource = ClassLoader.getSystemResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());

        List<Comparator<Product>> comparatorList = new ListProductComparators(sortCondition).getComparatorList(sortCondition);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }
    }

}
