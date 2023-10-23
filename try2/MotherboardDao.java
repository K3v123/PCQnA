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

// Data Access Object (DAO) class for accessing motherboard-related data in the database.
public class MotherboardDao {
    private Connection conn;

    // Constructor for the MotherboardDao.
    public MotherboardDao(Connection conn)  throws DatabaseOperationException {
        this.conn = conn;
    }

    // Retrieve a list of all motherboards from the database.
    public List<Motherboard> getAllMotherboards() throws DatabaseOperationException {
        List<Motherboard> motherboards = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id, type, size FROM MOTHERBOARD");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                motherboards.add(new Motherboard(rs.getString("ID"), rs.getString("TYPE"), rs.getString("SIZE")));
            }
        } catch (Exception e) {
            throw new DatabaseOperationException("Database operation failed.", e);
        }
        return motherboards;
    }
}