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

    private String type;
    private DatabaseManager dbManager;

    public CoolingModel(String id, String name, String type) {
        super(id, name);
        this.type = type;
    }

    public CoolingModel() {
        super("defaultID", "defaultName");
        this.type = "defaultType";
    }

    public String getType() {
        return type;
    }

    @Override
    public void loadFromDatabase() {
        // Mock implementation to load Cooling details from the database
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

    @Override
    public void displayOptions() {
        // Mock implementation to display Cooling options or recommendations
        if (this.type.equalsIgnoreCase("Liquid")) {
            System.out.println("Recommended for high-performance systems!");
        } else if (this.type.equalsIgnoreCase("Air")) {
            System.out.println("Ideal for standard user systems.");
        } else {
            System.out.println("No specific recommendations available.");
        }
    }
}
