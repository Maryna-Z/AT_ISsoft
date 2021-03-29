package com.marina;

import com.marina.domain.CategoryObj;
import com.marina.domain.ProductObj;
import com.marina.services.impl.ChainComparator;
import com.marina.services.impl.ListProductComparators;
import com.marina.services.impl.OrderBuilder;
import com.marina.services.impl.Store;
import com.marina.utility.DOMExecuter;
import com.marina.utility.Utils;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreApp {

    public static void main(String[] args) {

        String publisherType = args[0];
        Store store = new Store(publisherType);

        OrderBuilder orderBuilder = new OrderBuilder(store.getCategoryObjList());

        compareProducts(store.getCategoryObjList());

        int selectedProductId1 = Utils.getIntFromConsole(System.in);
        orderBuilder.createOrder(selectedProductId1);
        int selectedProductId2 = Utils.getIntFromConsole(System.in);
        orderBuilder.createOrder(selectedProductId2);
        orderBuilder.clearPerchesProduct();

    }

    public static Map<String, String> retrieveSortCondition() {
        URL resource = ClassLoader.getSystemResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        return executer.XMLreader(resource.getPath());
    }

    public static void compareProducts(List<CategoryObj> categories) {
        List<ProductObj> products = categories.stream()
                .map(categoryObj -> categoryObj.getProductObjList())
                .flatMap(productObjs -> productObjs.stream())
                .collect(Collectors.toList());

        Map<String, String> sortConditions = retrieveSortCondition();

        List<Comparator<ProductObj>> comparatorList = new ListProductComparators(sortConditions)
                .getComparatorList(sortConditions);

        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }
    }
}
