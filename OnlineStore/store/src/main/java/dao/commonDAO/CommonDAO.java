package dao.commonDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDAO {

    public void closeDBResources(Connection connection, Statement statement, ResultSet resultSet){
        closeConnection(connection);
        closeStatement(statement);
        closeResultSet(resultSet);
    }

    public void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: Connection has not been closed");
            }
        }
    }

    public void closeStatement(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error: Statement has not been closed");
            }
        }
    }

    protected void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error: ResultSet has not been closed");
            }
        }
    }
}
