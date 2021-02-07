package dao.builder;

public class QueryBuilder {
    public static String getAllCategories(){
        return "SELECT * FROM category";
    }

    public static  String deleteAllCategories(){
        return "DELETE FROM category";
    }
}
