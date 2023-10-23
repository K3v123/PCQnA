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
    // Constructs a StorageModel with default values.
    public StorageModel() {
        super("defaultID", "defaultType"); // Calling the superclass constructor with default values
        this.id = "defaultID";
        this.type = "defaultType";
        this.size = "defaultSize";
        this.speed = "defaultSpeed";
    }

    // Get the type of the storage.
    public String getType() {
        return type;
    }

    // Get the size of the storage.
    public String getSize() {
        return size;
    }

    // Get the speed of the storage.
    public String getSpeed() {
        return speed;
    }

    // Load storage details from the database based on the current ID.
    @Override
    public void loadFromDatabase() {
        String query = "SELECT type, size, speed FROM storages WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.type = resultSet.getString("type");
                this.size = resultSet.getString("size");
                this.speed = resultSet.getString("speed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch the top storage model with specific ID.
    public StorageModel fetchTopStorage() {
        StorageModel topStorage = null;
        String query = "SELECT id, type, size, speed FROM Storage WHERE id = '00027'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
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
