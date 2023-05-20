package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private Connection connection;
    public DatabaseManager() {
        connect();
    }

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/javapreview_2";
        String user = "root";
        String password = "admin";
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connect to database successfully.");
        }
        catch (Exception e) {
            System.err.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Close connection successfully.");
            }

            catch (Exception e) {
                System.err.println("Cannot close connection.");
                e.printStackTrace();
            }
        }
    }
}
