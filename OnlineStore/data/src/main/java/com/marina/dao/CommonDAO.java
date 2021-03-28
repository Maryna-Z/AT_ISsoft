package com.marina.dao;

import com.marina.builders.QueryBuilder;
import com.marina.utils.DatabaseConnection;
import com.marina.exceptions.DataException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonDAO<T> {

    public void insert(String query, List<T> list) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            completeStatement(statement, list);
            statement.executeBatch();
        } catch (Exception ex) {
            throw new DataException("Error to call insert method", ex);
        } finally {
            closeDBResources(connection, statement);
        }
    }

    public void update(String query) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            throw new DataException("Error to call update method", ex);
        } finally {
            closeDBResources(connection, statement);
        }
    }

    public List<T> load(String query) {
        List<T> returnList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            parseResultSet(rs, returnList);
        } catch (Exception ex) {
            throw new DataException("Error to call load method", ex);
        } finally {
            closeDBResources(connection, statement, rs);
        }
        return returnList;
    }

    public abstract void completeStatement(PreparedStatement statement, List<T> list);

    public abstract void parseResultSet(ResultSet resultSet, List<T> list) throws SQLException;

    private void closeDBResources(Connection connection, Statement statement, ResultSet resultSet) {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    private void closeDBResources(Connection connection, Statement statement) {
        closeStatement(statement);
        closeConnection(connection);
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataException("Error: Connection has not been closed");
            }
        }
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataException("Error: Statement has not been closed");
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error: ResultSet has not been closed");
            }
        }
    }

}
