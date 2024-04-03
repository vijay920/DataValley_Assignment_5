import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentStore {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/departments";
        String user = "username";
        String password = "password";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS department (" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(255)" +
                ")";

        String insertDataSQL = "INSERT INTO department (id, name) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            createTable(connection, createTableSQL);

            insertData(connection, insertDataSQL, 1, "IT");
            insertData(connection, insertDataSQL, 2, "HR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection, String createTableSQL) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    private static void insertData(Connection connection, String insertDataSQL, int id, String name)
            throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.executeUpdate();
        }
    }
}
