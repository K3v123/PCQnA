
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemoryModel extends Component {

    private String size;
    private DatabaseManager dbManager = new DatabaseManager();

    // Initializes a MemoryModel with the provided attributes.
    public MemoryModel(String id, String name, String size) {
        super(id, name);
        this.size = size;
    }

    // Default constructor for MemoryModel with default values.
    public MemoryModel() {
        super("defaultID", "defaultName");
        this.size = "defaultSize";
    }

    // Get the size of the memory.
    public String getSize() {
        return size;
    }

    // Set the size of the memory.
    public void setSize(String size) {
        this.size = size;
    }

    // Loads memory details from the database based on the ID.
    @Override
    public void loadFromDatabase() {
        String query = "SELECT size FROM memories WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.size = resultSet.getString("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch the top memory from the database.
    public MemoryModel fetchTopMemory() {
        MemoryModel topMemory = null;
        String query = "SELECT id, size FROM Memory WHERE id = '00019'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String size = resultSet.getString("size");
                topMemory = new MemoryModel(id, "", size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topMemory;
    }
}
