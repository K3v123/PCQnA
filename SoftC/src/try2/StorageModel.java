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

    private String type;
    private String size;
    private double speed;
    private DatabaseManager dbManager = new DatabaseManager();

    public StorageModel(String id, String name, String type, String size, double speed) {
        super(id, name);
        this.type = type;
        this.size = size;
        this.speed = speed;
    }

    public StorageModel() {
        super("defaultID", "defaultName");
        this.type = "defaultType";
        this.size = "defaultSize";
        this.speed = 0.0;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void loadFromDatabase() {
        // Mock implementation to load Storage details from the database
        String query = "SELECT type, size, speed FROM storages WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.type = resultSet.getString("type");
                this.size = resultSet.getString("size");
                this.speed = resultSet.getDouble("speed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayOptions() {
        // Mock implementation to display Storage options or recommendations
        if (this.type.equalsIgnoreCase("SSD")) {
            System.out.println("SSD: Faster read/write speed, suitable for OS and frequently accessed data.");
        } else if (this.type.equalsIgnoreCase("HDD")) {
            System.out.println("HDD: Slower read/write speed, more cost-effective for bulk storage.");
        } else if (this.type.equalsIgnoreCase("NVMe")) {
            System.out.println("NVMe: Very high speed, suitable for demanding applications and high-end builds.");
        } else {
            System.out.println("No specific recommendations available.");
        }
    }
}
