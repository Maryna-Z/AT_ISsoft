package com.marina.dao.commonDAO;

import com.marina.dao.DBConnection.DatabaseConnection;
import com.marina.Category;
import com.marina.dao.exceptions.DataException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {

    CommonDAO commonDAO = new CommonDAO();

    public void updateCategoryTable(String query) {
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
        }catch (DataException dataException){
            throw new DataException("Error: The Update/delete/insert records error");
        }finally {
            commonDAO.closeDBResources(connection, statement);
        }
    }

    public List<Category> loadCategories(String query){
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                categories.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (DataException dataException){
            throw new DataException("Error: The load records error");
        }finally {
            commonDAO.closeDBResources(connection, statement,rs);
        }
        return categories;
    }
}
