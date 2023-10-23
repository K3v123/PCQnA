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

// Data Access Object (DAO) for Power Supply Units (PSU). Provides methods to retrieve PSU data from the database.
public class PSUDao {

    private Connection conn;

    // Constructs a PSUDao with a database connection.
    public PSUDao(Connection connection) {
        this.conn = connection;
    }

    // Retrieves a list of all Power Supply Units (PSUs) from the database.
    public List<PowerSupplyModel> getAllPSUs() {
        List<PowerSupplyModel> psuList = new ArrayList<>();
        String query = "SELECT id, class FROM PowerSupply";  // Assuming table schema based on the given snippets
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PowerSupplyModel psu = new PowerSupplyModel(rs.getString("id"), rs.getString("class"));
                psuList.add(psu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return psuList;
    }

}
