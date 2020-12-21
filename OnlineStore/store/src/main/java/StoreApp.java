import org.xml.sax.SAXException;
import product.ChainComparator;
import product.ListProductComparators;
import product.Product;
import utility.DOMExecuter;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        RandomStorePopulator populator = new RandomStorePopulator();
        ArrayList<Product> products = populator.populateStore();
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
}
