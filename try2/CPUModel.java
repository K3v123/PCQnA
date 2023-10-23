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

    // Constructors
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

    // getters
    public String getSpeed() {
        return this.speed;
    }

    public String getOverclock() {
        return this.overclock;
    }

    // Overrides loadFromDatabase method
    @Override
    public void loadFromDatabase() {
        String query = "SELECT speed, overclock FROM CPU WHERE id = '" + this.id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        // to retrieve speed and overclock data from the result set
        try {
            if (resultSet.next()) {
                this.speed = resultSet.getString("speed");
                this.overclock = resultSet.getString("overclock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public CPUModel fetchTopCPU() {
        CPUModel topCPU = null;
        String query = "SELECT id, name, speed, overclock FROM CPU ORDER BY speed DESC FETCH FIRST ROW ONLY";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String overclock = resultSet.getString("overclock");
                topCPU = new CPUModel(id, name, speed, overclock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCPU;
    }

    public CPUModel fetchTopCPUById() {
        CPUModel topCPU = null;
        String query = "SELECT id, name, speed, overclock FROM CPU WHERE id = '00012'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String overclock = resultSet.getString("overclock");
                topCPU = new CPUModel(id, name, speed, overclock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCPU;
    }

    public CPUModel fetchCPUById(String id) {
        CPUModel cpu = null;
        String query = "SELECT id, name, speed, overclock FROM CPU WHERE id = '" + id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet.next()) {
                String fetchedId = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String overclock = resultSet.getString("overclock");
                cpu = new CPUModel(fetchedId, name, speed, overclock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpu;
    }
}
