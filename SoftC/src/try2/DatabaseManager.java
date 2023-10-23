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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private final String url = "jdbc:derby://localhost:1527/PCQnA";
    private final String user = "pdc";
    private final String password = "pdc";
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet fetchGPU() {
        return executeQuery("SELECT * FROM GPU");
    }

    public ResultSet fetchCPU() {
        return executeQuery("SELECT * FROM CPU");
    }

    public ResultSet fetchMemory() {
        return executeQuery("SELECT * FROM Memory");
    }

    public ResultSet fetchPowerSupply() {
        return executeQuery("SELECT * FROM PowerSupply");
    }

    public ResultSet fetchCooling() {
        return executeQuery("SELECT * FROM Cooling");
    }

    public ResultSet fetchStorage() {
        return executeQuery("SELECT * FROM Storage");
    }

    public ResultSet fetchTPU() {
        return executeQuery("SELECT * FROM TPU");
    }

    public ResultSet fetchMotherboard() {
        return executeQuery("SELECT * FROM Motherboard");
    }

    // Method to store the user's selection
    public void storeUserSelection(String componentType, int componentId) {
        try {
            String query = "INSERT INTO user_selections (component_type, component_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE component_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, componentType);
            pstmt.setInt(2, componentId);
            pstmt.setInt(3, componentId);  // for updating in case of duplicate
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the user's selection based on component type
    public int getUserSelection(String componentType) {
        int componentId = -1;
        try {
            String query = "SELECT component_id FROM user_selections WHERE component_type = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, componentType);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                componentId = rs.getInt("component_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return componentId;
    }

    // Method to get component details based on its type and ID
    public String getComponentDetails(String componentType, int componentId) {
        String details = "";
        try {
            String query = "SELECT * FROM " + componentType + " WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, componentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                details = rs.getString("name") + " " + rs.getString("other_column");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String query) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 to indicate no rows were affected if an exception is caught
    }

    // You might need a method to close the connection when done
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
