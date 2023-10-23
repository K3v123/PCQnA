
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

public class GPUModel extends Component {

    private String speed;
    protected String classification;
    private DatabaseManager dbManager = new DatabaseManager();

    // Constructors for this GPUModel class
    public GPUModel(String id, String name, String speed, String classification) {
        super(id, name);
        this.speed = speed;
        this.classification = classification;
    }

    public GPUModel() {
        super("defaultID", "defaultName");
        this.speed = "defaultSpeed";
        this.classification = "defaultClassification";
    }
    
    // getters and setters
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

    // Overrides the previous loadFromDatabase method and loads GPU information from the database.
    // Retrieves the speed and classification of the GPU with the specified ID.
    @Override
    public void loadFromDatabase() {
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

    // Fetches the top GPU from the database.
    // Retrieves the GPU with a specific ID and returns its details in a GPUModel.
    public GPUModel fetchTopGPU() {
        GPUModel topGPU = null;
        String query = "SELECT id, name, speed, class FROM GPU WHERE id = '0007'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String gpuClass = resultSet.getString("class");
                topGPU = new GPUModel(id, name, speed, gpuClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topGPU;
    }

//    public static List<GPUModel> fetchGPU() {
//        List<GPUModel> gpuList = new ArrayList<>();
//        String query = "SELECT id, name, speed, class FROM GPU";
//        ResultSet resultSet = dbManager.executeQuery(query);
//
//        try {
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//                String speed = resultSet.getString("speed");
//                String gpuClass = resultSet.getString("class");
//                GPUModel gpu = new GPUModel(id, name, speed, gpuClass);
//                gpuList.add(gpu);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return gpuList;
//    }

}
