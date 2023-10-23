/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Base.Component;
import Base.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemoryModel extends Component {

    private String size;
    private DatabaseManager dbManager = new DatabaseManager();

    // Constructor with parameters
    public MemoryModel(String id, String name, String size) {
        super(id, name);
        this.size = size;
    }

    // Default constructor
    public MemoryModel() {
        super("defaultID", "defaultName");
        this.size = "defaultSize";
    }

    // Getter for size
    public String getSize() {
        return size;
    }

    // Setter for size
    public void setSize(String size) {
        this.size = size;
    }

    // Load memory details from the database
    @Override
    public void loadFromDatabase() {
        String query = "SELECT size FROM memories WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
                this.size = resultSet.getString("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch the top memory component
    public MemoryModel fetchTopMemory() {
        MemoryModel topMemory = null;
        String query = "SELECT id, size FROM Memory WHERE id = '00019'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
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
