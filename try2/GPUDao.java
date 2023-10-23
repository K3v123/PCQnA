/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GPUDao {

    private Connection conn;

    // Constructor for the GPUDao class. Throws a DatabaseOperationException if an error occurs during database connection.
    public GPUDao(Connection connection)  throws DatabaseOperationException {
        this.conn = connection;
    }

    // Retrieves a list of all GPUs from the database.
    // Throws a DatabaseOperationException if an error occurs during the database operation.
    public List<GPU> getAllGPUs()  throws DatabaseOperationException {
        List<GPU> gpus = new ArrayList<>();
        String query = "SELECT id, name, speed FROM GPU";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GPU gpu = new GPU();
                gpu.setId(rs.getString("id"));
                gpu.setName(rs.getString("name"));
                gpu.setSpeed(rs.getDouble("speed"));
                gpu.setClazz("Placeholder or fetch from DB");
                
                gpus.add(gpu);
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to perform GPU database operation.");
e.printStackTrace();
throw new DatabaseOperationException("Failed to perform GPU database operation.", e);
        }
        return gpus;
    }

    // Retrieves a GPU by its unique ID from the database.
    // Throws a DatabaseOperationException if an error occurs during the database operation.
    public GPU getGPUById(String id)  throws DatabaseOperationException {
        GPU gpu = null;
        String query = "SELECT id, name, speed FROM GPU WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                gpu = new GPU();
                gpu.setId(rs.getString("id"));
                gpu.setName(rs.getString("name"));
                gpu.setSpeed(rs.getDouble("speed"));
                gpu.setClazz("Placeholder or fetch from DB");
                
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to perform GPU database operation.");
e.printStackTrace();
throw new DatabaseOperationException("Failed to perform GPU database operation.", e);
        }
        return gpu;
    }
    
}