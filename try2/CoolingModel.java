
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

public class CoolingModel extends Component {

    private String id;
    private String type;
    private DatabaseManager dbManager = new DatabaseManager();

    // Constructor that accepts id and type
    public CoolingModel(String id, String type) {
        super(id, type); // Calling the superclass constructor
        this.id = id;
        this.type = type;
    }

    // Default constructor
    public CoolingModel() {
        super("defaultID", "defaultType"); // Calling the superclass constructor with default values
        this.id = "defaultID";
        this.type = "defaultType";
    }

    // Getter and setter methods for id and type (add if they don't exist)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Fetches the top CoolingModel entry with a specific ID from the database
    public CoolingModel fetchTopCooling() {
        CoolingModel topCooling = null;
        String query = "SELECT id, type FROM Cooling WHERE id = '00024'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                topCooling = new CoolingModel(id, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCooling;
    }

    // Loads data for this CoolingModel from the database
    @Override
    public void loadFromDatabase() {
        String query = "SELECT type FROM cooling WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.type = resultSet.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
