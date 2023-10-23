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

public class MemoryModel extends Component {

    private String size;
    private DatabaseManager dbManager = new DatabaseManager();

    public MemoryModel(String id, String name, String size) {
        super(id, name);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Getters, setters...
    @Override
    public void loadFromDatabase() {
        // Mock implementation to load Memory details from the database
        String query = "SELECT size FROM memories WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.size = resultSet.getString("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayOptions() {
        // Mock implementation to display Memory options or recommendations
        if (this.size.equalsIgnoreCase("8GB")) {
            System.out.println("Suitable for basic multitasking.");
        } else if (this.size.equalsIgnoreCase("16GB")) {
            System.out.println("Good for gaming and moderate multitasking.");
        } else if (this.size.equalsIgnoreCase("32GB")) {
            System.out.println("Ideal for intensive multitasking, video editing, and graphic design.");
        } else if (this.size.equalsIgnoreCase("64GB")) {
            System.out.println("Overkill for most tasks, but perfect for high-end workstations.");
        } else {
            System.out.println("No specific recommendations available.");
        }
    }
}
