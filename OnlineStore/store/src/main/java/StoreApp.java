import utility.DBActions;
import utility.DatabaseConnection;

import java.util.*;


public class StoreApp {
    public static void main(String[] args) {
        Properties prop = DatabaseConnection.prop;
        prop.forEach((k, v) -> System.out.println(v.toString()));
        String query = "SHOW TABLES";
        String query2 = "SELECT * FROM category";
        String query3 = "INSERT INTO category (categoryName) VALUES ('TT');";
        String query4 = "INSERT INTO category (categoryName) VALUES ('BB');";
        DBActions.execUpdatedStatement(query3);
        DBActions.execStatement(query);
        DBActions.closeConnection();
        DBActions.loadCategories(query2);
        DBActions.execUpdatedStatement(query4);

        /*try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useUnicode=true&serverTimezone=UTC", "root", "11111Aa!");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from category");

            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getInt(2));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/


        /*Store store = new Store(3000);
        RandomStorePopulator populator = new RandomStorePopulator();
        List<Product> products = populator.populateStore(90);
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
        store.setProducts(products);

        URL resource = StoreApp.class.getResource("sort.xml");
        DOMExecuter executer = new DOMExecuter();
        Map<String, String> sortCondition = executer.XMLreader(resource.getPath());

        List<Comparator<Product>> comparatorList = new ListProductComparators(sortCondition).getComparatorList(sortCondition);
        products.sort(new ChainComparator(comparatorList));
        System.out.println("\n sorting ********************************\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(products.get(i));
        }

        CreateThreadFunctionality threadFunctionality = new CreateThreadFunctionality(store);
        threadFunctionality.createOrders();
        threadFunctionality.cleanBasket();*/
    }




}
