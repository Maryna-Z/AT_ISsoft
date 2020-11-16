import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class SAXParser {
    private static ArrayList<Product> products = new ArrayList<>();

    public SAXParser(ArrayList<Product> products){
        this.products = products;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        RandomStorePopulator populator = new RandomStorePopulator();
        ArrayList<Product> products = populator.populateStore();

        Store store = new Store(products);
        ArrayList<Product> sorting = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("c:\\Users\\user\\Java\\AT_ISsoft\\AT_ISsoft\\OnlineStore\\store\\src\\main\\resources\\sort.xml"));
        Node sortElement = document.getElementsByTagName("sort").item(0);
        NodeList sortConditions = sortElement.getChildNodes();


        for (int i = 0; i < sortConditions.getLength(); i++){
            Node condition = sortConditions.item(i);
            String nodeName = condition.getNodeName();

            switch (nodeName){
                case "name":
                    if (condition.getNodeValue().toLowerCase() == "asc"){
                        sorting = store.sortByNameAsc();
                    } else if (condition.getNodeValue().toLowerCase() == "desc"){
                        sorting = store.sortByNameDec();
                    }
                    break;
                case "price":
                    if (condition.getNodeValue().toLowerCase() == "asc"){
                        sorting = store.sortByPriceAscending();
                    } else if (condition.getNodeValue().toLowerCase() == "desc"){
                        sorting = store.sortByPriceDescending();
                    }
                    break;
                case "rating":
                    if (condition.getNodeValue().toLowerCase() == "asc"){
                        sorting = store.sortByRatingAscending();
                    } else if (condition.getNodeValue().toLowerCase() == "desc"){
                        sorting = store.sortByRatingDescending();

                    }
                    break;
            }
        }
    }
}

