package utility;

import com.mysql.cj.jdbc.MysqlDataSource;
import other.CategoryDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBConnect {

    

    private static String path = "c:/Users/user/Java/AT_ISsoft/AT_ISsoft/OnlineStore/store/src/main/resources/db.properties";
    public static Properties prop = getProperties(path);

    private static Properties getProperties(String path){
        Properties prop = new Properties();
        //URL resource = DBConnect.class.getResource("db.properties");
        //try(InputStream input = new FileInputStream(resource.getPath())){
        try(InputStream input = new FileInputStream(path)){
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    private static MysqlDataSource getDataSource(Properties prop) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(prop.getProperty("HOST"));
        dataSource.setPort(Integer.parseInt(prop.getProperty("PORT")));
        dataSource.setUser(prop.getProperty("USER"));
        dataSource.setPassword(prop.getProperty("PWD"));
        dataSource.setDatabaseName(prop.getProperty("DBNAME"));
        return dataSource;
    }

    public static void execStatement(String query) {
        try(Connection connection = getDataSource(prop).getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)) {

            if (rs.next()){
                System.out.println(rs.getString(1));
            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void loadCategories(String query){
        List<CategoryDB> categories = new ArrayList<CategoryDB>();

        try (Connection connection = getDataSource(prop).getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()){
                categories.add(new CategoryDB(rs.getInt(1), rs.getString(2)));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        categories.forEach(categoryDB -> System.out.println(categoryDB.toString()));
    }


}
