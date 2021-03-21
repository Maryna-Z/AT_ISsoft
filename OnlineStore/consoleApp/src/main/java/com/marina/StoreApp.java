package com.marina;

import com.marina.services.ProductPublisher;
import com.marina.services.impl.Store;

import java.util.List;


public class StoreApp {

    public static void main(String[] args) {

        String publisherType = args[0];
        ProductPublisher publisher = new Store().getPublisher(publisherType);
        List<Product> products = publisher.getProducts();
        System.out.println(products);

        /*
        URL resource = com.marina.StoreApp.class.getResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());

        List<Comparator<Product>> comparatorList = new ListProductComparators(sortCondition).getComparatorList(sortCondition);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }

        com.marina.services.impl.CreateThreadFunctionality threadFunctionality = new com.marina.services.impl.CreateThreadFunctionality(store);
        threadFunctionality.createOrders();
        threadFunctionality.cleanBasket();*/

    }

}
