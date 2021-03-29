package com.marina.utils;

import com.marina.exceptions.DataException;
import com.marina.exceptions.InitializationException;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection uniqueInstance;
    private Connection connection;

    private static String path = "data/src/main/resources/db.properties";
    public static Properties prop = getProperties(path);

    private DatabaseConnection(){
        try {
            this.connection = getDataSource(prop).getConnection();
        } catch (SQLException e) {
            throw new DataException("Something wrong with data source", e);
        }
    };

    public synchronized static DatabaseConnection getInstance() throws SQLException {
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
            throw new InitializationException("Error with loading property");
        }
        return prop;
    }

    private MysqlDataSource getDataSource(Properties prop){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(prop.getProperty("HOST"));
        dataSource.setPort(Integer.parseInt(prop.getProperty("PORT")));
        dataSource.setUser(prop.getProperty("USER"));
        dataSource.setPassword(prop.getProperty("PWD"));
        dataSource.setDatabaseName(prop.getProperty("DBNAME"));
        return dataSource;
    }

}
