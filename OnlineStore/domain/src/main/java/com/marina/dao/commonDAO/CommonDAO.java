package com.marina.dao.commonDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDAO {

    public void closeDBResources(Connection connection, Statement statement, ResultSet resultSet){
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    public void closeDBResources(Connection connection, Statement statement){
        closeStatement(statement);
        closeConnection(connection);
    }

    private void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: Connection has not been closed");
            }
        }
    }

    private void closeStatement(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error: Statement has not been closed");
            }
        }
    }

    private void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error: ResultSet has not been closed");
            }
        }
    }
}
