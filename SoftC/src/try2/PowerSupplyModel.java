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

public class PowerSupplyModel extends Component {

    private String classification;
    private DatabaseManager dbManager = new DatabaseManager();

    public PowerSupplyModel(String id, String name, String classification) {
        super(id, name);
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    @Override
    public void loadFromDatabase() {
        // Mock implementation to load PowerSupply details from the database
        String query = "SELECT classification FROM power_supplies WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.classification = resultSet.getString("classification");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayOptions() {
        // Mock implementation to display PowerSupply options or recommendations
        if (this.classification.equalsIgnoreCase("Bronze")) {
            System.out.println("Bronze: Basic efficiency level, suitable for budget builds.");
        } else if (this.classification.equalsIgnoreCase("Silver")) {
            System.out.println("Silver: Moderate efficiency, suitable for mainstream builds.");
        } else if (this.classification.equalsIgnoreCase("Gold")) {
            System.out.println("Gold: High efficiency, suitable for high-end builds.");
        } else if (this.classification.equalsIgnoreCase("Platinum")) {
            System.out.println("Platinum: Very high efficiency, suitable for enthusiast builds.");
        } else {
            System.out.println("No specific recommendations available.");
        }
    }
}