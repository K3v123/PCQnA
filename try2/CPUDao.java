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

public class CPUDao {

    private Connection conn;

    // This part is a constructor which throws an exception in case of an error
    public CPUDao(Connection connection)  throws DatabaseOperationException {
        this.conn = connection;
    }

    // Retrieves a list of all CPUs from the database and throws an exception in case any error occurs during database operation
    public List<CPU> getAllCPUs()  throws DatabaseOperationException {
        List<CPU> cpus = new ArrayList<>();
        String query = "SELECT id, name, speed, overclock FROM CPU";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Execute the SQL query and retrieve results & Iterate through the result set
            ResultSet rs = stmt.executeQuery();  
            while (rs.next()) { 
                CPU cpu = new CPU();  
                // Set the CPU's components from the result
                cpu.setId(rs.getString("id"));  
                cpu.setName(rs.getString("name"));  
                cpu.setSpeed(rs.getDouble("speed"));  
                cpu.setOverclock(rs.getBoolean("overclock"));  
                cpus.add(cpu);  // Add the CPU to the list of CPUs
            }
        } catch (SQLException e) {  // Catch potential SQL-related exceptions and Prints the exception's stack trace for debugging
            System.out.println("Error: Failed to retrieve CPUs from the database.");  
            e.printStackTrace();
            // Rethrow the exception as a custom DatabaseOperationException
            throw new DatabaseOperationException("Failed to retrieve CPUs from the database.", e);  
        }
        return cpus;
    }

    // This throws exception for Database opertaion
    public CPU getCPUById(String id)  throws DatabaseOperationException {

    // If the id is null, then it throws new exception 
    if (id == null || id.trim().isEmpty()) {
        throw new IllegalArgumentException("Provided CPU ID is null or empty.");
    }

    CPU cpu = null;
    // Defines SQL query and prepares SQL statement
    String query = "SELECT id, name, speed, overclock FROM CPU WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        // Executes query and gets results
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            cpu = new CPU();
            cpu.setId(rs.getString("id"));
            cpu.setName(rs.getString("name"));
            cpu.setSpeed(rs.getDouble("speed"));
            cpu.setOverclock(rs.getBoolean("overclock"));
        }
    } 
    // Handles SQL exceptions
    catch (SQLException e) {
        System.out.println("Error: Failed to retrieve CPUs from the database.");
        e.printStackTrace();
        throw new DatabaseOperationException("Failed to retrieve CPUs from the database.", e);
    }
    return cpu;
    
    }

}