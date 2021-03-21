package com.marina.dao.commonDAO;

import com.marina.dao.DBConnection.DatabaseConnection;
import com.marina.Product;
import com.marina.dao.builder.QueryBuilder;
import com.marina.dao.exceptions.DataException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {

    CommonDAO commonDAO = new CommonDAO();

    public void updateProductTable(String query) {
        Connection connection = null;
        Statement statement = null;
        int result;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            result = statement.executeUpdate(query);
            System.out.println(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (DataException dataException) {
            throw new DataException("Error: The Update/delete/insert records error");
        } finally {
            commonDAO.closeDBResources(connection, statement);
        }
    }

    public void insertProducts(List<Product> products) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(QueryBuilder.insertProductQuery());
            products.forEach(product -> {
            });
            for (int i = 0; i < products.size(); i++) {
                statement.setString(1, products.get(i).getName());
                statement.setInt(2, products.get(i).getRating());
                statement.setDouble(3, products.get(i).getPrice());
                statement.setInt(4, products.get(i).getCategoryID());
                statement.setInt(5, products.get(i).getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            throw new DataException("Error: The insert products error");
        } finally {
            commonDAO.closeDBResources(connection, statement);
        }
    }

    public List<Product> loadProducts(String query) {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getInt(6)
                );
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (DataException dataException) {
            throw new DataException("Error: The load records error");
        } finally {
            commonDAO.closeDBResources(connection, statement, rs);
        }
        return products;
    }

}
