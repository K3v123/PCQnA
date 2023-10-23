/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Base.Component;
import Base.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoolingModel extends Component {

    private String id;
    private String type;
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Constructor for the `CoolingModel` class that accepts an ID and a type.
     *
     * @param id The unique identifier of the cooling component.
     * @param type The type or category of the cooling component.
     */
    public CoolingModel(String id, String type) {
        super(id, type); // Calling the superclass constructor
        this.id = id;
        this.type = type;
    }

    /**
     * Default constructor for the `CoolingModel` class with default values.
     */
    public CoolingModel() {
        super("defaultID", "defaultType"); // Calling the superclass constructor with default values
        this.id = "defaultID";
        this.type = "defaultType";
    }

    /**
     * Getter method for retrieving the ID of the cooling component.
     *
     * @return The ID of the cooling component.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for setting the ID of the cooling component.
     *
     * @param id The ID to be set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for retrieving the type of the cooling component.
     *
     * @return The type of the cooling component.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for setting the type of the cooling component.
     *
     * @param type The type to be set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Fetches the top cooling component with a specific ID from the database.
     *
     * @return The top cooling component retrieved from the database.
     */
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

    /**
     * Loads cooling component details from the database into the model.
     */
    @Override
    public void loadFromDatabase() {
        String query = "SELECT type FROM cooling WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
                this.type = resultSet.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
