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
    private String overclock;
    private DatabaseManager dbManager = new DatabaseManager();

    public CPUModel(String id, String name, String speed, String overclock) {
        super(id, name);
        this.speed = speed;
        this.overclock = overclock;
    }

    public CPUModel() {
        super("defaultID", "defaultName");
        this.speed = "defaultSpeed";
        this.overclock = "defaultOverclock";
    }

    public String getSpeed() {
        return this.speed;
    }

    public String getOverclock() {
        return this.overclock;
    }

    @Override
    public void loadFromDatabase() {
        // Mock implementation to load CPU details from the database
        String query = "SELECT speed, overclock FROM CPU WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                this.speed = resultSet.getString("speed");
                this.overclock = resultSet.getString("overclock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayOptions() {
        // Mock implementation to display CPU options or recommendations
        System.out.println("Speed: " + this.speed);
        System.out.println("Overclock: " + this.overclock);
    }
}
