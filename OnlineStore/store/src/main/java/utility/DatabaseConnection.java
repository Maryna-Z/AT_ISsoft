package utility;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private volatile static DatabaseConnection uniqueInstance;
    private Connection connection;

    private static String path = "c:/Users/user/Java/AT_ISsoft/AT_ISsoft/OnlineStore/store/src/main/resources/db.properties";
    public static Properties prop = getProperties(path);

    private DatabaseConnection(){
        try {
            this.connection = getDataSource(prop).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public static DatabaseConnection getInstance(){
        if (uniqueInstance == null) {
            uniqueInstance = new DatabaseConnection();
        } else {
            try {
                if (uniqueInstance.getConnection().isClosed()) {
                    uniqueInstance = new DatabaseConnection();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return uniqueInstance;
    }

    /*public static DatabaseConnection getInstance(){
        if (uniqueInstance == null){
            synchronized (DatabaseConnection.class){
                if (uniqueInstance == null){
                    uniqueInstance = new DatabaseConnection();
                }
            }
        }
        return uniqueInstance;
    }*/

    public Connection getConnection(){
        return connection;
    }

    private static Properties getProperties(String path){
        Properties prop = new Properties();
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

}
