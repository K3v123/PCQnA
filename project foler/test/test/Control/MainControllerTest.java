/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Control;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import view.MainView;

/**
 *
 * @author kq635
 */
/**
 * Test class for MainController. This class aims to test the behavior of the
 * MainController, especially in relation to how it interacts with the MainView.
 */
public class MainControllerTest {
    // Instance of the MainController to be tested

    private MainController controller;

    // Mock version of the MainView to help verify interactions
    private MockMainView mockMainView;

    /**
     * Set up method executed before each test. Initializes a mock MainView and
     * a MainController instance.
     */
    @Before
    public void setUp() {
        mockMainView = new MockMainView();
        controller = new MainController(mockMainView);
    }

    /**
     * Test to verify if listeners are attached on the initialization of the
     * controller.
     */
    @Test
    public void testListenersAreAttachedOnInitialization() {
        assertTrue(mockMainView.gpuButtonListenerAdded);
        assertTrue(mockMainView.motherboardButtonListenerAdded);
        assertTrue(mockMainView.tpuButtonListenerAdded);
        assertTrue(mockMainView.storageButtonListenerAdded);
        assertTrue(mockMainView.coolingButtonListenerAdded);
        assertTrue(mockMainView.powerSupplyButtonListenerAdded);
        assertTrue(mockMainView.memoryButtonListenerAdded);
        assertTrue(mockMainView.cpuButtonListenerAdded);
        assertTrue(mockMainView.mostAskedButtonListenerAdded);
        assertTrue(mockMainView.adminButtonListenerAdded);
        assertTrue(mockMainView.topSetupsButtonListenerAdded);
    }

    /**
     * Mock class for MainView. This mock class is designed to capture and
     * verify interactions, especially the attachment of listeners.
     */
    private class MockMainView extends MainView {

        // Flags to track if specific listeners are attached
        boolean gpuButtonListenerAdded = false;
        boolean motherboardButtonListenerAdded = false;
        boolean tpuButtonListenerAdded = false;
        boolean storageButtonListenerAdded = false;
        boolean coolingButtonListenerAdded = false;
        boolean powerSupplyButtonListenerAdded = false;
        boolean memoryButtonListenerAdded = false;
        boolean cpuButtonListenerAdded = false;
        boolean mostAskedButtonListenerAdded = false;
        boolean adminButtonListenerAdded = false;
        boolean topSetupsButtonListenerAdded = false;

        // Overridden methods to set the flags when listeners are attached
        @Override
        public void addGPUButtonListener(java.awt.event.ActionListener listener) {
            gpuButtonListenerAdded = true;
        }

        @Override
        public void addMotherboardButtonListener(java.awt.event.ActionListener listener) {
            motherboardButtonListenerAdded = true;
        }

        @Override
        public void addTPUButtonListener(java.awt.event.ActionListener listener) {
            tpuButtonListenerAdded = true;
        }

        @Override
        public void addStorageButtonListener(java.awt.event.ActionListener listener) {
            storageButtonListenerAdded = true;
        }

        @Override
        public void addCoolingButtonListener(java.awt.event.ActionListener listener) {
            coolingButtonListenerAdded = true;
        }

        @Override
        public void addPowerSupplyButtonListener(java.awt.event.ActionListener listener) {
            powerSupplyButtonListenerAdded = true;
        }

        @Override
        public void addMemoryButtonListener(java.awt.event.ActionListener listener) {
            memoryButtonListenerAdded = true;
        }

        @Override
        public void addCPUButtonListener(java.awt.event.ActionListener listener) {
            cpuButtonListenerAdded = true;
        }

        @Override
        public void addMostAskedButtonListener(java.awt.event.ActionListener listener) {
            mostAskedButtonListenerAdded = true;
        }

        @Override
        public void addAdminButtonListener(java.awt.event.ActionListener listener) {
            adminButtonListenerAdded = true;
        }

        @Override
        public void addTopSetupsButtonListener(java.awt.event.ActionListener listener) {
            topSetupsButtonListenerAdded = true;
        }
    }
}
