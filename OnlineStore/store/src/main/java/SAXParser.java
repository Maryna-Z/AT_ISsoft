import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class SAXParserM {
    private static ArrayList<Product> products = new ArrayList<>();

    public SAXParserM(ArrayList<Product> products){
        this.products = products;
    }

    public void SAXparse(String path) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
    }


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

