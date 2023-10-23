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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoolingDao {
    private Connection conn;

    // This is for throwing the database operation exception
    public CoolingDao(Connection conn)  throws DatabaseOperationException {
        this.conn = conn;
    }

    // Retrieves a list of Cooling objects from the database
    public List<Cooling> getAllCoolings() throws DatabaseOperationException {
        List<Cooling> coolings = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id, type FROM COOLING");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coolings.add(new Cooling(rs.getString("ID"), rs.getString("TYPE")));
            }
        } catch (Exception e) {
            throw new DatabaseOperationException("Database operation failed.", e);
        }
        return coolings;
    } 
    
}