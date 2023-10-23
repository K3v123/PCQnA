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

public class MotherboardModel extends Component {

    private String type;
    private String size;
    private DatabaseManager dbManager = new DatabaseManager();

    public MotherboardModel(String id, String type, String size) {
        super(id, "defaultName");  // This calls the constructor of the Component class
        this.type = type;
        this.size = size;
    }

    public MotherboardModel() {
        super("defaultID", "defaultName");
        this.type = "defaultType";
        this.size = "defaultSize";
    }

    // getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Loads motherboard details from the database for the current MotherboardModel.
    @Override
    public void loadFromDatabase() {
        // Mock implementation to load Motherboard details from the database
        String query = "SELECT type, size FROM motherboards WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.type = resultSet.getString("type");
                this.size = resultSet.getString("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
