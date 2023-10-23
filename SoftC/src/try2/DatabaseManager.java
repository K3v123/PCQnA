/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:derby:yourDatabaseName;create=true";
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database.");
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing the query.");
            return null;
        }
    }

    public int executeUpdate(String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating the database.");
            return 0;
        }
    }

    public String fetchGPURecommendation(String gpuName) {
        // Mock implementation: Return a recommendation based on GPU name
        // In a real-world scenario, you would fetch this data from the database.

        switch (gpuName) {
            case "RTX 4090":
                return "Highly recommended for gaming!";
            case "RTX 4050":
                return "Good for basic gaming!";
            // ... add more cases for other GPU names as needed
            default:
                return "No recommendation available.";
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error closing the connection.");
        }
    }
}
