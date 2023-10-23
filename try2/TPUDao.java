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
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TPUDao {

    // The connection to the database
    private Connection conn;

    // Constructs a TPUDao with a database connection.
    public TPUDao(Connection connection) {
        this.conn = connection;
    }

    // Retrieves a list of all TPUs from the database.
    public List<TPU> getAllTPUs() {
        List<TPU> tpus = new ArrayList<>();
        String query = "SELECT * FROM TPU";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TPU tpu = new TPU();
                // Set the TPU's ID from the result set                 
                tpu.setId(rs.getString("id"));
                // Set the TPU's type from the result set
                tpu.setType(rs.getString("type"));
                // Set the TPU's speed from the result set
                tpu.setSpeed(rs.getDouble("speed"));
                tpus.add(tpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tpus;
    }

}
