/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseManager {

    private final String url = "jdbc:derby://localhost:1527/PCQnA";
    private final String user = "pdc";
    private final String password = "pdc";
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    // Fetches the top 10 most asked components

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
//    // Fetches the top 5 most asked components
//    public List<String> getMostAskedComponents() {
//        List<String> results = new ArrayList<>();
//        // Using FETCH FIRST 5 ROWS ONLY for Derby database:
//        String sql = "SELECT OptionName, Count FROM MostAsked ORDER BY Count DESC FETCH FIRST 5 ROWS ONLY";
//        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                results.add(rs.getString("OptionName") + " - " + rs.getInt("Count") + " times");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return results;
//    }

    //  Fetches GPU data from the database and returns it as a list of GPUModel objects.
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

    // Fetches CPU data from the database and returns it as a list of CPUModel objects.
    public List<CPUModel> fetchCPU() {
        List<CPUModel> cpuList = new ArrayList<>();
        String query = "SELECT * FROM CPU";
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String speed = resultSet.getString("speed");
                String overclock = resultSet.getString("overclock");
                CPUModel cpu = new CPUModel(id, name, speed, overclock);
                cpuList.add(cpu);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpuList;
    }

    // Fetches CPU data from the database based on the given ID.
    public CPUModel getCPUById(String id) {
        System.out.println("[DatabaseManager] Fetching CPU with ID: " + id);
        String query = "SELECT * FROM CPU WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String speed = rs.getString("speed");
                    String overclock = rs.getString("overclock");
                    return new CPUModel(id, name, speed, overclock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fetches a list of motherboard data from the database.
    public List<MotherboardModel> fetchMotherboard() {
        List<MotherboardModel> motherboards = new ArrayList<>();

        String query = "SELECT * FROM MOTHERBOARD";

        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("ID"); // Changed to fetch ID as a String
                String type = rs.getString("TYPE");
                String size = rs.getString("SIZE");
                motherboards.add(new MotherboardModel(id, type, size));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motherboards;
    }

    // Fetches a list of cooling data from the database.
    public List<CoolingModel> fetchCooling() {
        List<CoolingModel> coolingList = new ArrayList<>();
        String query = "SELECT * FROM COOLING";
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                CoolingModel cooling = new CoolingModel(id, type);
                coolingList.add(cooling);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coolingList;
    }

    // Fetches a list of TPU data from the database.
    public List<TPUModel> fetchTPU() {
        List<TPUModel> tpuList = new ArrayList<>();
        String query = "SELECT * FROM TPU";
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                String speed = resultSet.getString("speed");
                TPUModel tpu = new TPUModel(id, type, speed);
                tpuList.add(tpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tpuList;
    }

    // Fetches a list of storage data from the database.
    public List<StorageModel> fetchStorage() {
        List<StorageModel> storageList = new ArrayList<>();
        String query = "SELECT * FROM Storage";  // Assuming the table name is "Storage"
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                String size = resultSet.getString("size");
                String speed = resultSet.getString("speed");
                StorageModel storage = new StorageModel(id, type, size, speed);
                storageList.add(storage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storageList;
    }

    // Fetches a list of memory data from the database.
    public List<MemoryModel> fetchMemory() {
        List<MemoryModel> memoryList = new ArrayList<>();
        String query = "SELECT * FROM Memory";  // Assuming the table name is "Memory"
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String size = resultSet.getString("size");
                MemoryModel memory = new MemoryModel(id, "", size);  // Assuming the MemoryModel constructor accepts ID, name, and size
                memoryList.add(memory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memoryList;
    }

    // Fetches a list of power supply data from the database using a provided DatabaseManager instance.
    public static List<PowerSupplyModel> fetchPowerSupplies(DatabaseManager dbManager) {
        List<PowerSupplyModel> powerSupplyList = new ArrayList<>();
        String query = "SELECT id, class FROM PowerSupply";
        ResultSet resultSet = dbManager.executeQuery(query);

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String powerClass = resultSet.getString("class");
                PowerSupplyModel powerSupply = new PowerSupplyModel(id, powerClass);
                powerSupplyList.add(powerSupply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return powerSupplyList;
    }

    // Method to get component details based on its type and ID
    public String getComponentDetails(String componentType, int componentId) {
        String details = "";
        String query = "SELECT * FROM " + componentType + " WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, componentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    details = rs.getString("name");
                    // You can add other details as needed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    // Executes a database query and returns the ResultSet.
    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Executes a database update query and returns the number of rows affected.
    public int executeUpdate(String query) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 to indicate no rows were affected if an exception is caught
    }

    // You might need a method to close the connection when done
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch the most asked options
    public List<String> fetchMostAskedOptions() {
        List<String> mostAskedOptions = new ArrayList<>();
        String sql = "SELECT OptionName FROM MostAsked ORDER BY Count DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mostAskedOptions.add(rs.getString("OptionName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostAskedOptions;
    }

    public Connection getConnection() throws SQLException {
        // Assuming JDBC connection. Adjust connection string, user, and password as needed.
        String url = "jdbc:derby:myDB;create=true;";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
