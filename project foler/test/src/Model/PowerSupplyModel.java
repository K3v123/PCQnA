/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Base.Component;
import Base.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PowerSupplyModel extends Component {

    private String powerClass;
    private DatabaseManager dbManager = new DatabaseManager();

    // Constructor with parameters
    public PowerSupplyModel(String id, String powerClass) {
        super(id, "");
        this.powerClass = powerClass;
    }

    // Default constructor
    public PowerSupplyModel() {
        super("defaultID", "");
        this.powerClass = "defaultClass";
    }

    // Getter for powerClass
    public String getPowerClass() {
        return powerClass;
    }

    // Setter for powerClass
    public void setPowerClass(String powerClass) {
        this.powerClass = powerClass;
    }

    // Method to load power supply data from the database based on the ID
    @Override
    public void loadFromDatabase() {
        String query = "SELECT class FROM PowerSupply WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
                this.powerClass = resultSet.getString("class");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch a specific power supply (e.g., top power supply) based on a predefined ID
    public PowerSupplyModel fetchTopPowerSupply() {
        PowerSupplyModel topPowerSupply = null;
        String query = "SELECT id, class FROM PowerSupply WHERE id = '00020'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String powerClass = resultSet.getString("class");
                topPowerSupply = new PowerSupplyModel(id, powerClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topPowerSupply;
    }

    // Method to fetch a list of power supplies from the database
    public static List<PowerSupplyModel> fetchPowerSupplies(DatabaseManager dbManager) {
        List<PowerSupplyModel> powerSupplyList = new ArrayList<>();
        String query = "SELECT id, class FROM PowerSupply";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String powerClass = resultSet.getString("class");
                PowerSupplyModel powerSupply = new PowerSupplyModel(id, powerClass);
                powerSupplyList.add(powerSupply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return powerSupplyList;
    }

}
