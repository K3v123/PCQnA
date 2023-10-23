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

public class GPUModel extends Component {

    private String speed;
    private String classification;
     private DatabaseManager dbManager = new DatabaseManager();  

    public GPUModel(String id, String name, String speed, String classification) {
        super(id, name);
        this.speed = speed;
        this.classification = classification;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public void loadFromDatabase() {
        // Mock implementation to load GPU details from the database
        String query = "SELECT speed, classification FROM gpus WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.speed = resultSet.getString("speed");
                this.classification = resultSet.getString("classification");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayOptions() {
        // Mock implementation to display GPU options or recommendations
        if (this.classification.equalsIgnoreCase("High-end")) {
            System.out.println("Perfect for intensive graphical tasks, like 3D rendering and gaming.");
        } else if (this.classification.equalsIgnoreCase("Mid-range")) {
            System.out.println("Great for day-to-day tasks with occasional gaming.");
        } else if (this.classification.equalsIgnoreCase("Low-end")) {
            System.out.println("Suitable for basic tasks like browsing and document editing.");
        } else {
            System.out.println("No specific recommendations available.");
        }
    }
}
