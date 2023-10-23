/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Base.Component;
import Base.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageModel extends Component {

    private String id;
    private String type;
    private String size;
    private String speed;  // Changing speed to String
    private DatabaseManager dbManager = new DatabaseManager();

    // Constructor that accepts id, type, size, and speed
    public StorageModel(String id, String type, String size, String speed) {
        super(id, type); // Calling the superclass constructor
        this.id = id;
        this.type = type;
        this.size = size;
        this.speed = speed;
    }

    // Default constructor
    public StorageModel() {
        super("defaultID", "defaultType"); // Calling the superclass constructor with default values
        this.id = "defaultID";
        this.type = "defaultType";
        this.size = "defaultSize";
        this.speed = "defaultSpeed";
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getSpeed() {
        return speed;
    }

    @Override
    public void loadFromDatabase() {
        // Query the database to load storage details based on the ID
        String query = "SELECT type, size, speed FROM storages WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                // Update the attributes from the database
                this.type = resultSet.getString("type");
                this.size = resultSet.getString("size");
                this.speed = resultSet.getString("speed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StorageModel fetchTopStorage() {
        StorageModel topStorage = null;
        // Query the database to fetch the top storage component
        String query = "SELECT id, type, size, speed FROM Storage WHERE id = '00027'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
                // Retrieve attributes from the query result
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                String size = resultSet.getString("size");
                String speed = resultSet.getString("speed");  // Directly use the speed string
                topStorage = new StorageModel(id, type, size, speed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topStorage;
    }
}
