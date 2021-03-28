package com.marina;

import com.marina.services.impl.OrderBuilder;
import com.marina.services.impl.Store;
import com.marina.utility.DOMExecuter;
import com.marina.utility.Utils;

import java.net.URL;
import java.util.Map;

public class StoreApp {

    public static void main(String[] args) {

        String publisherType = args[0];
        Store store = new Store(publisherType);
        OrderBuilder orderBuilder = new OrderBuilder(store.getCategoryObjList());

        /*Map<String, String> sortConditions = retrieveSortCondition();
        List<Comparator<Product>> comparatorList = new ListProductComparators(sortConditions)
                .getComparatorList(sortConditions);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }*/

        int selectedProductId1 = Utils.getIntFromConsole(System.in);
        orderBuilder.createOrder(selectedProductId1);
        int selectedProductId2 = Utils.getIntFromConsole(System.in);
        orderBuilder.createOrder(selectedProductId2);
        orderBuilder.clearPerchesProduct();

    }

    public static Map<String, String> retrieveSortCondition(){
        URL resource = ClassLoader.getSystemResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        return executer.XMLreader(resource.getPath());
    }
}
