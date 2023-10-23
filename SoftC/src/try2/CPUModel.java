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

public class CPUModel extends Component {

    private String speed;
    private boolean overclock;

    public CPUModel(String id, String name, String speed, boolean overclock) {
        super(id, name);
        this.speed = speed;
        this.overclock = overclock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public boolean isOverclock() {
        return overclock;
    }

    public void setOverclock(boolean overclock) {
        this.overclock = overclock;
    }

    @Override
    public void loadFromDatabase() {
        // Example: Fetch details from the database using DatabaseManager and populate the model's fields.
        DatabaseManager dbManager = new DatabaseManager();
        ResultSet rs = dbManager.executeQuery("SELECT * FROM cpus WHERE id = '" + this.id + "'");
        try {
            if (rs.next()) {
                this.name = rs.getString("name");
                this.speed = rs.getString("speed");
                this.overclock = rs.getBoolean("overclock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.closeConnection();
        }
    }

    @Override
    public void displayOptions() {
        // For the sake of this example, just print the details. 
        // In a more complex application, you might want to show a UI component or something else.
        System.out.println("Name: " + this.name);
        System.out.println("Speed: " + this.speed);
        System.out.println("Overclock: " + (this.overclock ? "Yes" : "No"));
    }

}
