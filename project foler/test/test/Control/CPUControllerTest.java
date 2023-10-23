/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Control;

import Base.DatabaseManager;
import Model.CPUModel;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import view.CPUView;
import view.MainView;

/**
 *
 * @author kq635
 */
/**
 * Test class for CPUController
 */
public class CPUControllerTest {

    // Instance variables for test setup
    private CPUModel cpuModel;
    private CPUView cpuView;
    private MainViewStub mainViewStub;
    private DatabaseManagerStub databaseManagerStub;
    private CPUController cpuController;

    /**
     * This method is executed before each test. It sets up the necessary
     * objects for testing.
     */
    @Before
    public void setUp() {
        // Initialize the required objects and stubs
        cpuModel = new CPUModel();
        mainViewStub = new MainViewStub();
        cpuView = new CPUView(mainViewStub);  // Using the actual CPUView
        databaseManagerStub = new DatabaseManagerStub();

        // Create an instance of CPUController with the stubbed and actual dependencies
        cpuController = new CPUController(cpuModel, cpuView, mainViewStub);
        // Set the stubbed DatabaseManager in the controller
        cpuController.setDatabaseManager(databaseManagerStub);
    }

    /**
     * Tests the behavior of GoBackListener in CPUController.
     */
    @Test
    public void testGoBackButtonListener() {
        // Create a mock action event
        ActionEvent actionEvent = new ActionEvent(this, 0, "");

        // Simulate the "Go Back" button click
        cpuController.new GoBackListener().actionPerformed(actionEvent);

        // Assert that the CPUView is hidden and the main view is shown
        assertFalse(cpuView.getFrame().isVisible());
        assertTrue(mainViewStub.isVisibleCalled());
    }

    /**
     * Tests the behavior of FetchDetailsListener in CPUController.
     */
    @Test
    public void testFetchDetailsButtonListener() {
        // Create a mock action event
        ActionEvent actionEvent = new ActionEvent(this, 0, "");

        // Simulate the "Fetch Details" button click
        cpuController.new FetchDetailsListener().actionPerformed(actionEvent);

        // Assert that the CPU details were fetched and the table in CPUView was set up
        assertTrue(databaseManagerStub.isFetchCPUCalled());
        assertNotNull(cpuView.getCPUTable().getModel());
    }

    /**
     * Stub class for MainView. Used to simulate the MainView's behavior without
     * the actual implementation.
     */
    class MainViewStub extends MainView {

        private boolean isVisibleCalled = false;

        @Override
        public void setVisible(boolean b) {
            isVisibleCalled = true;
        }

        public boolean isVisibleCalled() {
            return isVisibleCalled;
        }
    }

    /**
     * Stub class for DatabaseManager. Used to simulate the DatabaseManager's
     * behavior without the actual implementation.
     */
    class DatabaseManagerStub extends DatabaseManager {

        private boolean fetchCPUCalled = false;

        @Override
        public List<CPUModel> fetchCPU() {
            fetchCPUCalled = true;
            CPUModel dummyModel = new CPUModel();
            return Arrays.asList(dummyModel);
        }

        public boolean isFetchCPUCalled() {
            return fetchCPUCalled;
        }
    }
}
