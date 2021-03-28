package com.marina.dao;

import com.marina.entities.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoriesDAO extends CommonDAO<Category> {

    @Override
    public void completeStatement(PreparedStatement statement, List<Category> list) {
    }

    @Override
    public void parseResultSet(ResultSet resultSet, List<Category> list) throws SQLException {
        while (resultSet.next()) {
            list.add(new Category(resultSet.getInt(1), resultSet.getString(2)));
        }
    }
}
