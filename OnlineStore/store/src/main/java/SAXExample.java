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

public class SAXExample {
    private static ArrayList<Product> products = new ArrayList<>();

    public SAXExample(ArrayList<Product> products){
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
        NodeList sortConditions = document.getElementsByTagName("sort");

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
        /*SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        parser.parse(new File("c:\\Users\\user\\Java\\AT_ISsoft\\AT_ISsoft\\OnlineStore\\store\\src\\main\\resources\\xml_file2.xml"), handler);

        for (Product product : products)
            System.out.println(product);
    }*/

    /*private static class AdvancedXMLHandler extends DefaultHandler {
        private String name, price, rating, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("name"))
                    name = information;
                if (lastElementName.equals("price"))
                    price = information;
                if (lastElementName.equals("rating"))
                    rating = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (name != null && !name.isEmpty()) && (price != null && !price.isEmpty()) && (rating != null && !rating.isEmpty()) ) {
                products.add(new Product.Builder().withName(name).withPrice(Double.valueOf(price)).withRating(Integer.valueOf(rating)).build());
                name = null;
                price = null;
            }
        }
    }*/
