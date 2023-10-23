/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Base;

import Model.StorageModel;
import Model.TPUModel;
import Model.PowerSupplyModel;
import Model.MotherboardModel;
import Model.MemoryModel;
import Model.GPUModel;
import Base.DatabaseManager;
import Model.CoolingModel;
import Model.CPUModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 *
 * @author kq635
 */
/*Test class for DatabaseManager.
 * This class contains unit tests that ensure the functionality of DatabaseManager methods.
*/
public class DatabaseManagerTest {

    // Instance of DatabaseManager to be used in the tests
    private DatabaseManager dbManager;

    /**
     * Sets up the test environment before each test method. Initializes the
     * DatabaseManager instance.
     */
    @Before
    public void setUp() {
        dbManager = new DatabaseManager();
    }

    /**
     * Clean up resources after each test method. Closes the database
     * connection.
     */
    @After
    public void tearDown() {
        dbManager.closeConnection();
    }

    /**
     * Test to ensure that a non-empty list of most asked components is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfMostAskedComponents() {
        List<String> components = dbManager.getMostAskedComponents();
        assertNotNull(components);
        assertTrue(components.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of GPUs is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfGPUs() {
        List<GPUModel> gpus = dbManager.fetchGPU();
        assertNotNull(gpus);
        assertTrue(gpus.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of CPUs is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfCPUs() {
        List<CPUModel> cpus = dbManager.fetchCPU();
        assertNotNull(cpus);
        assertTrue(cpus.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of motherboards is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfMotherboards() {
        List<MotherboardModel> motherboards = dbManager.fetchMotherboard();
        assertNotNull(motherboards);
        assertTrue(motherboards.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of cooling components is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfCoolingComponents() {
        List<CoolingModel> coolings = dbManager.fetchCooling();
        assertNotNull(coolings);
        assertTrue(coolings.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of TPUs is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfTPUs() {
        List<TPUModel> tpus = dbManager.fetchTPU();
        assertNotNull(tpus);
        assertTrue(tpus.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of storage devices is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfStorageDevices() {
        List<StorageModel> storages = dbManager.fetchStorage();
        assertNotNull(storages);
        assertTrue(storages.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of memory components is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfMemoryComponents() {
        List<MemoryModel> memories = dbManager.fetchMemory();
        assertNotNull(memories);
        assertTrue(memories.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of power supplies is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfPowerSupplies() {
        List<PowerSupplyModel> powerSupplies = DatabaseManager.fetchPowerSupplies(dbManager);
        assertNotNull(powerSupplies);
        assertTrue(powerSupplies.size() > 0);
    }

    /**
     * Test to ensure that a non-empty list of most asked options is fetched.
     */
    @Test
    public void shouldFetchNonEmptyListOfMostAskedOptions() {
        List<String> options = dbManager.fetchMostAskedOptions();
        assertNotNull(options);
        assertTrue(options.size() > 0);
    }

}
