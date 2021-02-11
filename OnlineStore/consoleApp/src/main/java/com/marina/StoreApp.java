package com.marina;


import com.marina.dao.builder.QueryBuilder;
import com.marina.dao.commonDAO.products.ProductsDAO;
import com.marina.dao.domain.Category;
import com.marina.dao.domain.Product;

import java.util.*;


public class StoreApp {

    public static void main(String[] args){
        List<Category> categories = new ArrayList<>();
        System.out.println(categories);
        ProductsDAO productsDAO = new ProductsDAO();


        /*Properties prop = DatabaseConnection.prop;
        prop.forEach((k, v) -> System.out.println(v.toString()));
        DBActions actions = new DBActions();
        String query = "SHOW TABLES";
        String query2 = "SELECT * FROM category";
        String query3 = "INSERT INTO category (categoryName) VALUES ('TT');";
        String query4 = "INSERT INTO category (categoryName) VALUES ('BB');";
        String query5 = "DELETE FROM category WHERE categoryName = 'TT'";*/
        //actions.execUpdatedStatement(query5);

        //actions.loadCategories(QueryBuilder.getAllCategories());



        Store store = new Store(3000);
        RandomStoreFilling populator = new RandomStoreFilling();
        List<Product> products = populator.populateStoreFromDBCategory(90);
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
        store.setProducts(products);
        productsDAO.insertProducts(QueryBuilder.insertProductQuery(), products);

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

        com.marina.CreateThreadFunctionality threadFunctionality = new com.marina.CreateThreadFunctionality(store);
        threadFunctionality.createOrders();
        threadFunctionality.cleanBasket();*/
    }

}
