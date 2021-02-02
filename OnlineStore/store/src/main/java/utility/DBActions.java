package utility;
import other.CategoryDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBActions {

    private static DatabaseConnection connection = DatabaseConnection.getInstance();

    public static void execStatement(String query) {
        try(Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query)) {

            if (rs.next()){
                System.out.println(rs.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void execUpdatedStatement(String query) {
        try {
            Connection con = connection.getConnection();
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(query);
            System.out.println(result);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void loadCategories(String query){
        List<CategoryDB> categories = new ArrayList<CategoryDB>();

        try (Connection con = connection.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()){
                categories.add(new CategoryDB(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        categories.forEach(categoryDB -> System.out.println(categoryDB.toString()));
    }

    public static void closeConnection() {
        try {
            connection.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
