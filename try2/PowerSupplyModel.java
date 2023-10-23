
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
import java.util.ArrayList;
import java.util.List;

// Represents a Power Supply Unit (PSU) model. Extends the Component class and provides methods to manage PSU data.
public class PowerSupplyModel extends Component {

    private String powerClass;
    private DatabaseManager dbManager = new DatabaseManager();

    // Constructs a PowerSupplyModel with an ID and power class.
    public PowerSupplyModel(String id, String powerClass) {
        super(id, "");
        this.powerClass = powerClass;
    }

    // Default constructor to create a PowerSupplyModel with default values.
    public PowerSupplyModel() {
        super("defaultID", "");
        this.powerClass = "defaultClass";
    }

    // Gets the power class of the PSU.
    public String getPowerClass() {
        return powerClass;
    }

    // Sets the power class of the PSU.
    public void setPowerClass(String powerClass) {
        this.powerClass = powerClass;
    }

    // Loads PSU data from the database based on the ID.
    @Override
    public void loadFromDatabase() {
        String query = "SELECT class FROM PowerSupply WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.powerClass = resultSet.getString("class");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetches the top Power Supply Unit (PSU) based on a predefined ID.
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

    // Fetches a list of Power Supply Units (PSUs) from the database using the provided DatabaseManager.
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
