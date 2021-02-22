package com.marina.dao.DBConnection;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection uniqueInstance;
    private Connection connection;

    private static String path = "c:/Users/user/Java/AT_ISsoft/AT_ISsoft/OnlineStore/dao/src/main/resources/db.properties";
    public static Properties prop = getProperties(path);

    private DatabaseConnection(){
        try {
            this.connection = getDataSource(prop).getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with data source", e);
        }
    };

    public static DatabaseConnection getInstance() throws SQLException {
        if (uniqueInstance == null) {
            uniqueInstance = new DatabaseConnection();
        } else {
            if (uniqueInstance.getConnection().isClosed()) {
                uniqueInstance = new DatabaseConnection();
            }
        }
        return uniqueInstance;
    }

    public Connection getConnection(){
        return connection;
    }

    private static Properties getProperties(String path){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("Error with get property");;
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
