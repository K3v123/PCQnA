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

    public MotherboardModel(String id, String name, String type, String size) {
        super(id, name);
        this.type = type;
        this.size = size;
    }

    public MotherboardModel() {
        super("defaultID", "defaultName");
        this.type = "defaultType";
        this.size = "defaultSize";
    }

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

    @Override
    public void displayOptions() {
        // Mock implementation to display Motherboard options or recommendations
        if (this.size.equalsIgnoreCase("ATX")) {
            System.out.println("ATX: Standard size, suitable for most builds.");
        } else if (this.size.equalsIgnoreCase("MicroATX")) {
            System.out.println("MicroATX: Smaller than ATX, but has fewer expansion slots.");
        } else if (this.size.equalsIgnoreCase("Mini-ITX")) {
            System.out.println("Mini-ITX: Very compact size, suitable for small form factor builds.");
        } else {
            System.out.println("No specific recommendations available.");
        }
    }
}
