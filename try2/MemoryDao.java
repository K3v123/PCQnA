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

public class MemoryDao {

    private Connection conn;

    // Initializes a MemoryDao with a database connection.
    public MemoryDao(Connection connection)  throws DatabaseOperationException {
        this.conn = connection;
    }

    // Retrieves a list of all memory components from the database.
    public List<Memory> getAllMemories()  throws DatabaseOperationException {
        List<Memory> memories = new ArrayList<>();
        String query = "SELECT id, type FROM Memory";  // Assuming the table name is "Memory"
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Memory memory = new Memory();
                memory.setId(rs.getString("id"));
                memory.setType(rs.getString("type"));
                memories.add(memory);
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to perform Memory database operation.");
e.printStackTrace();
throw new DatabaseOperationException("Failed to perform Memory database operation.", e);
        }
        return memories;
    } 

}