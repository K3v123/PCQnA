/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import Model.CPUModel;
import Model.CoolingModel;
import Model.GPUModel;
import Model.MemoryModel;
import Model.MotherboardModel;
import Model.PowerSupplyModel;
import Model.StorageModel;
import Model.TPUModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // Database connection properties
    private final String url = "jdbc:derby://localhost:1527/PCQnA";
    private final String user = "pdc";
    private final String password = "pdc";
    private Connection conn;

    /**
     * Constructor for DatabaseManager. Initializes a database connection.
     */
    public DatabaseManager() {
        try {
            // Establish a database connection
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Increments the count for a given option name in the database. If the
     * option doesn't exist, it inserts a new row.
     *
     * @param optionName The name of the option to increment the count for.
     */
    public void incrementCountForOption(String optionName) {
        try {
            // Check if the option exists
            String checkSql = "SELECT COUNT(*) FROM MostAsked WHERE OptionName = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, optionName);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // If it exists, update the count
                String updateSql = "UPDATE MostAsked SET Count = Count + 1 WHERE OptionName = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, optionName);
                updateStmt.executeUpdate();
            } else {
                // If it doesn't exist, insert a new row
                String insertSql = "INSERT INTO MostAsked (OptionName, Count) VALUES (?, 1)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, optionName);
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches the top 8 most asked components from the database.
     *
     * @return A list of strings representing the most asked components.
     */
    public List<String> getMostAskedComponents() {
        List<String> results = new ArrayList<>();
        // Using FETCH FIRST 10 ROWS ONLY for Derby database:
        String sql = "SELECT OptionName, Count FROM MostAsked ORDER BY Count DESC FETCH FIRST 10 ROWS ONLY";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(rs.getString("OptionName") + " - " + rs.getInt("Count") + " times");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Fetches a list of GPU components from the database.
     *
     * @return A list of GPUModel objects representing GPU components.
     */
    public List<GPUModel> fetchGPU() {
        List<GPUModel> gpuList = new ArrayList<>();
        String query = "SELECT * FROM GPU";
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String classification = resultSet.getString("class");
                GPUModel gpu = new GPUModel(id, name, speed, classification);
                gpuList.add(gpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpuList;
    }

    /**
     * Fetches a list of CPU components from the database.
     *
     * @return A list of CPUModel objects representing CPU components.
     */
    public List<CPUModel> fetchCPU() {
        List<CPUModel> cpuList = new ArrayList<>();
        String query = "SELECT * FROM CPU"; // SQL query to select all CPU records
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id"); // Get CPU ID from the result set
                String name = resultSet.getString("name"); // Get CPU name from the result set
                String speed = resultSet.getString("speed"); // Get CPU speed from the result set
                String overclock = resultSet.getString("overclock"); // Get CPU overclock status from the result set
                CPUModel cpu = new CPUModel(id, name, speed, overclock); // Create a CPUModel object
                cpuList.add(cpu); // Add the CPUModel object to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return cpuList; // Return the list of CPU components
    }

    /**
     * Fetches a CPU component from the database by its unique ID.
     *
     * @param id The ID of the CPU component to fetch.
     * @return A CPUModel object representing the CPU component, or null if not
     * found.
     */
    public CPUModel getCPUById(String id) {
        System.out.println("[DatabaseManager] Fetching CPU with ID: " + id); // Print a message indicating the ID being fetched
        String query = "SELECT * FROM CPU WHERE id = ?"; // SQL query to select a CPU record by ID
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id); // Set the ID as a parameter in the SQL query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name"); // Get CPU name from the result set
                    String speed = rs.getString("speed"); // Get CPU speed from the result set
                    String overclock = rs.getString("overclock"); // Get CPU overclock status from the result set
                    return new CPUModel(id, name, speed, overclock); // Create and return a CPUModel object
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return null; // Return null if the CPU with the specified ID is not found
    }

    /**
     * Fetches a list of motherboard components from the database.
     *
     * @return A list of MotherboardModel objects representing motherboard
     * components.
     */
    public List<MotherboardModel> fetchMotherboard() {
        List<MotherboardModel> motherboards = new ArrayList<>();

        String query = "SELECT * FROM MOTHERBOARD"; // SQL query to select all motherboard records

        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("ID"); // Fetch motherboard ID from the result set as a string
                String type = rs.getString("TYPE"); // Fetch motherboard type from the result set
                String size = rs.getString("SIZE"); // Fetch motherboard size from the result set
                motherboards.add(new MotherboardModel(id, type, size)); // Create a MotherboardModel object and add it to the list
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }

        return motherboards; // Return the list of motherboard components
    }

    /**
     * Fetches a list of cooling components from the database.
     *
     * @return A list of CoolingModel objects representing cooling components.
     */
    public List<CoolingModel> fetchCooling() {
        List<CoolingModel> coolingList = new ArrayList<>();
        String query = "SELECT * FROM COOLING"; // SQL query to select all cooling records
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id"); // Get cooling ID from the result set
                String type = resultSet.getString("type"); // Get cooling type from the result set
                CoolingModel cooling = new CoolingModel(id, type); // Create a CoolingModel object
                coolingList.add(cooling); // Add the CoolingModel object to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return coolingList; // Return the list of cooling components
    }

    /**
     * Fetches a list of TPU (Tensor Processing Unit) components from the
     * database.
     *
     * @return A list of TPUModel objects representing TPU components.
     */
    public List<TPUModel> fetchTPU() {
        List<TPUModel> tpuList = new ArrayList<>();
        String query = "SELECT * FROM TPU"; // SQL query to select all TPU records
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id"); // Get TPU ID from the result set
                String type = resultSet.getString("type"); // Get TPU type from the result set
                String speed = resultSet.getString("speed"); // Get TPU speed from the result set
                TPUModel tpu = new TPUModel(id, type, speed); // Create a TPUModel object
                tpuList.add(tpu); // Add the TPUModel object to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return tpuList; // Return the list of TPU components
    }

    /**
     * Fetches a list of storage components from the database.
     *
     * @return A list of StorageModel objects representing storage components.
     */
    public List<StorageModel> fetchStorage() {
        List<StorageModel> storageList = new ArrayList<>();
        String query = "SELECT * FROM Storage";  // SQL query to select all storage records from the "Storage" table
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id"); // Get storage ID from the result set
                String type = resultSet.getString("type"); // Get storage type from the result set
                String size = resultSet.getString("size"); // Get storage size from the result set
                String speed = resultSet.getString("speed"); // Get storage speed from the result set
                StorageModel storage = new StorageModel(id, type, size, speed); // Create a StorageModel object
                storageList.add(storage); // Add the StorageModel object to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return storageList; // Return the list of storage components
    }

    /**
     * Fetches a list of memory components from the database.
     *
     * @return A list of MemoryModel objects representing memory components.
     */
    public List<MemoryModel> fetchMemory() {
        List<MemoryModel> memoryList = new ArrayList<>();
        String query = "SELECT * FROM Memory";  // SQL query to select all memory records from the "Memory" table
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id"); // Get memory ID from the result set
                String size = resultSet.getString("size"); // Get memory size from the result set

                // Assuming the MemoryModel constructor accepts ID, name, and size
                MemoryModel memory = new MemoryModel(id, "", size);

                memoryList.add(memory); // Add the MemoryModel object to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return memoryList; // Return the list of memory components
    }

    /**
     * Fetches a list of power supply components from the database.
     *
     * @param dbManager The DatabaseManager instance used for executing the
     * query.
     * @return A list of PowerSupplyModel objects representing power supply
     * components.
     */
    public static List<PowerSupplyModel> fetchPowerSupplies(DatabaseManager dbManager) {
        List<PowerSupplyModel> powerSupplyList = new ArrayList<>();
        String query = "SELECT id, class FROM PowerSupply"; // SQL query to select power supply IDs and classes
        ResultSet resultSet = dbManager.executeQuery(query); // Execute the query using the provided DatabaseManager

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id"); // Get power supply ID from the result set
                String powerClass = resultSet.getString("class"); // Get power supply class from the result set
                PowerSupplyModel powerSupply = new PowerSupplyModel(id, powerClass); // Create a PowerSupplyModel object
                powerSupplyList.add(powerSupply); // Add the PowerSupplyModel object to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return powerSupplyList; // Return the list of power supply components
    }

    /**
     * Retrieves details of a component based on its type and ID from the
     * database.
     *
     * @param componentType The type of component (e.g., CPU, GPU, etc.).
     * @param componentId The unique ID of the component.
     * @return A string containing details of the component, or an empty string
     * if not found.
     */
    public String getComponentDetails(String componentType, int componentId) {
        String details = ""; // Initialize the details string as empty
        String query = "SELECT * FROM " + componentType + " WHERE id = ?"; // SQL query to select a component by ID and type
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, componentId); // Set the component ID as a parameter in the SQL query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    details = rs.getString("name"); // Get the name of the component from the result set
                    // You can add other details as needed by fetching additional columns from the result set
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return details; // Return the details of the component, or an empty string if not found
    }

    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executes an SQL update query (e.g., INSERT, UPDATE, DELETE) on the
     * database.
     *
     * @param query The SQL query to be executed.
     * @return The number of rows affected by the query, or 0 if an exception is
     * caught.
     */
    public int executeUpdate(String query) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query); // Prepare the SQL statement
            return pstmt.executeUpdate(); // Execute the update query and return the number of affected rows
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return 0; // Return 0 to indicate no rows were affected if an exception is caught
    }

    /**
     * Closes the database connection when it's no longer needed.
     */
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close(); // Close the database connection
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur when closing the connection
        }
    }

    /**
     * Fetches the most asked options from the database.
     *
     * @return A list of option names ordered by count in descending order.
     */
    public List<String> fetchMostAskedOptions() {
        List<String> mostAskedOptions = new ArrayList<>();
        String sql = "SELECT OptionName FROM MostAsked ORDER BY Count DESC"; // SQL query to select most asked options
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mostAskedOptions.add(rs.getString("OptionName")); // Add option names to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return mostAskedOptions; // Return the list of most asked options
    }

    /**
     * Gets a database connection using the specified connection parameters.
     *
     * @return A database connection.
     * @throws SQLException If a SQL exception occurs during connection.
     */
    public Connection getConnection() throws SQLException {
        // Assuming JDBC connection. Adjust connection string, user, and password as needed.
        String url = "jdbc:derby:myDB;create=true;"; // Modify the URL as needed
        Connection conn = DriverManager.getConnection(url); // Establish the database connection
        return conn; // Return the connection
    }

    /**
     * Executes an SQL update query with parameters and returns the number of
     * affected rows.
     *
     * @param query The SQL query to be executed with parameters.
     * @param params The parameter values to be set in the query.
     * @return The number of rows affected by the query, or 0 if an exception is
     * caught.
     */
    public int executeUpdateWithParams(String query, String... params) {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]); // Set the parameters in the query
            }
            return stmt.executeUpdate(); // Execute the update query with parameters and return the number of affected rows
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace(); // Print the exception stack trace for debugging
            return 0; // Return 0 to indicate no rows were affected if an exception is caught
        }
    }

    /**
     * Retrieves the count for a specific option from the database.
     *
     * @param optionName The name of the option for which to retrieve the count.
     * @return The count of the specified option, or 0 if not found or an
     * exception is caught.
     */
    public int getCountForOption(String optionName) {
        int count = 0; // Initialize the count as 0
        String sql = "SELECT Count FROM MostAsked WHERE OptionName = ?"; // SQL query to select count for the specified option
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, optionName); // Set the optionName as a parameter in the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count"); // Get the count from the result set
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions that may occur
        }
        return count; // Return the count of the specified option, or 0 if not found or an exception is caught
    }

}
