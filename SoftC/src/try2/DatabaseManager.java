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
import java.util.ArrayList;
import java.util.List;

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

    public List<GPUModel> fetchGPU() {
        List<GPUModel> gpuList = new ArrayList<>();
        String query = "SELECT * FROM gpus";
        ResultSet resultSet = executeQuery(query);

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String classification = resultSet.getString("classification");
                GPUModel gpu = new GPUModel(id, name, speed, classification);
                gpuList.add(gpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpuList;
    }

    public List<CPUModel> fetchCPU() {
        List<CPUModel> cpuList = new ArrayList<>();
        String query = "SELECT * FROM CPU";
        ResultSet resultSet = executeQuery(query);

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String overclock = resultSet.getString("overclock");
                CPUModel cpu = new CPUModel(id, name, speed, overclock);
                cpuList.add(cpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpuList;
    }
    // ... (More methods related to fetching other components can be added similarly)

    public int getMostSelectedComponentId(String componentType) {
        int componentId = -1;
        try {
            String query = "SELECT component_id FROM selections WHERE component_type = ?";
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


