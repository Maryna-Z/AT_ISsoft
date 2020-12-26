import product.ChainComparator;
import product.ListProductComparators;
import product.Product;
import utility.DOMExecuter;


import java.net.URL;
import java.util.*;

public class StoreApp {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        RandomStorePopulator populator = new RandomStorePopulator();
        List<Product> products = populator.populateStore();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }

        URL resource = StoreApp.class.getResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());

        List<Comparator<Product>> comparatorList = new ListProductComparators(sortCondition).getComparatorList(sortCondition);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }
    }

    /*public String getProductNameByCategory(String categoryName){
        String productName;
        Faker faker = new Faker();
        switch (categoryName.toLowerCase()){
            case "milk":
                productName = faker.food().ingredient();
        }
    }*/

}
