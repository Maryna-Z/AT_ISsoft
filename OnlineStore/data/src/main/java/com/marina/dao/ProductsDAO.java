package com.marina.dao;

import com.marina.entities.Product;
import com.marina.exceptions.DataException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductsDAO extends CommonDAO<Product> {
    @Override
    public void completeStatement(PreparedStatement statement, List<Product> list) {
        list.forEach(product -> {
            try {
                statement.setString(1,product.getName());
                statement.setInt(2, product.getRating());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, product.getCategoryId());
                statement.setInt(5, product.getQuantity());
                statement.addBatch();
            } catch (SQLException ex) {
                throw new DataException("Error to completeStatement method for Product");
            }

        });
    }

    @Override
    public void parseResultSet(ResultSet resultSet, List<Product> list) throws SQLException {
        while (resultSet.next()) {
            Product product = new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6)
            );
            list.add(product);
        }
    }
}
