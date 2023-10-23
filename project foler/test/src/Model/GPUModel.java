/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Base.Component;
import Base.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GPUModel extends Component {

    private String speed;
    protected String classification;
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Constructs a GPUModel with specified attributes.
     *
     * @param id The ID of the GPU.
     * @param name The name of the GPU.
     * @param speed The speed of the GPU.
     * @param classification The classification of the GPU.
     */
    public GPUModel(String id, String name, String speed, String classification) {
        super(id, name); // Call the superclass constructor with ID and name
        this.speed = speed;
        this.classification = classification;
    }

    /**
     * Constructs a default GPUModel with default attribute values.
     */
    public GPUModel() {
        super("defaultID", "defaultName"); // Call the superclass constructor with default ID and name
        this.speed = "defaultSpeed";
        this.classification = "defaultClassification";
    }

    /**
     * Gets the speed of the GPU.
     *
     * @return The speed of the GPU.
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the GPU.
     *
     * @param speed The speed of the GPU to set.
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     * Gets the classification of the GPU.
     *
     * @return The classification of the GPU.
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the classification of the GPU.
     *
     * @param classification The classification of the GPU to set.
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * Loads GPU information from the database based on the GPU's ID.
     */
    @Override
    public void loadFromDatabase() {
        String query = "SELECT speed, classification FROM gpus WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
                this.speed = resultSet.getString("speed"); // Load speed from the database
                this.classification = resultSet.getString("classification"); // Load classification from the database
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches information for the top GPU from the database.
     *
     * @return The top GPU as a GPUModel object, or null if not found.
     */
    public GPUModel fetchTopGPU() {
        GPUModel topGPU = null;
        String query = "SELECT id, name, speed, class FROM GPU WHERE id = '0007'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
                // Retrieve GPU attributes from the database result set
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String gpuClass = resultSet.getString("class");

                // Create a GPUModel instance with the fetched attributes
                topGPU = new GPUModel(id, name, speed, gpuClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topGPU;
    }

}
