import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        RandomStorePopulator populator = new RandomStorePopulator();
        ArrayList<Product> products = populator.populateStore();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }

        URL resource = StoreApp.class.getResource("sort.xml");
        //String path = "c:\\Users\\user\\Java\\AT_ISsoft\\AT_ISsoft\\OnlineStore\\store\\src\\main\\resources\\sort.xml";
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());
        //Map<String, String> sortCondition = executer.XMLreader(path);

        Store store = new Store(products);
        store.multipleSort(sortCondition);
        ArrayList<Product> newProduct = store.sortProduct(sortCondition, "price");


        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(newProduct.get(i));
        }
        Commands commands = new Commands();
        commands.quit("quit".trim().toLowerCase());
        //StoreApp app = new StoreApp();
        //app.multipleSort(sortCondition);
        //System.out.println(sortCondition);

    }

    public void  multipleSort(Map<String, String> map) {
        for (Map.Entry<String, String> item : map.entrySet()) {
            String value = item.getValue().trim().toLowerCase();
            String key = item.getKey().trim().toLowerCase();
            System.out.println("switch start");
            switch (key) {

                case "name":
                    if (value.equals("asc")) {
                        System.out.println("case name value asc");
                    } else if (value == "desc") {
                        System.out.println("case name value desc");
                    }
                    break;
                case "price":
                    if (value.equals("asc")) {
                        System.out.println("case price value asc");
                    } else if (value.equals("desc")) {
                        System.out.println("case price value desc");
                    }
                    break;
                case "rating":
                    if (value.equals("asc")) {
                        System.out.println("case rating value asc");
                    } else if (value.equals("desc")) {
                        System.out.println("case rating value desc");
                    }
                    break;
            }
        }
    }
}
