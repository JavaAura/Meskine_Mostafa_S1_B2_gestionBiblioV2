package metier.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private DbConnection() {}

    private static Connection connection = null;

    public static Connection getInstance() {
        if (connection == null) {
            try {
                String url = "jdbc:postgresql://localhost:5432/Jiblio_v2";
                String user = "postgres";
                String password = "mesmos2001";

                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Error connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}
