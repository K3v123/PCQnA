/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Base.Component;
import Base.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CPUModel extends Component {

    private String speed;
    private String overclock;
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Parameterized constructor to create a `CPUModel` object with specific
     * attributes.
     *
     * @param id The unique identifier for the CPU.
     * @param name The name of the CPU.
     * @param speed The speed of the CPU.
     * @param overclock The overclocking capabilities of the CPU.
     */
    public CPUModel(String id, String name, String speed, String overclock) {
        super(id, name);
        this.speed = speed;
        this.overclock = overclock;
    }

    /**
     * Default constructor to create a `CPUModel` object with default attribute
     * values.
     */
    public CPUModel() {
        super("defaultID", "defaultName");
        this.speed = "defaultSpeed";
        this.overclock = "defaultOverclock";
    }

    /**
     * Get the CPU speed.
     *
     * @return The speed of the CPU.
     */
    public String getSpeed() {
        return this.speed;
    }

    /**
     * Get the CPU overclocking capabilities.
     *
     * @return The overclocking capabilities of the CPU.
     */
    public String getOverclock() {
        return this.overclock;
    }

    /**
     * Load CPU data from a database based on the CPU's unique ID. This method
     * fetches CPU speed and overclocking information from the database.
     */
    @Override
    public void loadFromDatabase() {
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

    /**
     * Fetch the top-performing CPU from the database based on speed.
     *
     * @return The top-performing CPU as a `CPUModel` object.
     */
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

    /**
     * Fetch a specific CPU from the database based on its unique ID.
     *
     * @return The CPU as a `CPUModel` object.
     */
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

    /**
     * Fetch a specific CPU from the database based on its unique ID.
     *
     * @param id The unique ID of the CPU to fetch.
     * @return The CPU as a `CPUModel` object.
     */
    public CPUModel fetchCPUById(String id) {
        CPUModel cpu = null;
        String query = "SELECT id, name, speed, overclock FROM CPU WHERE id = '" + id + "'";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            if (resultSet != null && resultSet.next()) {
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
