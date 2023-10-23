/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package view;

import Model.CPUModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kq635
 */
public class CPUViewTest {

    private CPUView cpuView;
    private MainViewStub mainViewStub;
    private ActionEvent actionEvent;

    @Before
    public void setUp() {
        // Create instances of CPUView, MainViewStub, and ActionEvent for testing.
        mainViewStub = new MainViewStub();
        cpuView = new CPUView(mainViewStub);
        actionEvent = new ActionEvent(this, 0, "");
    }

    /**
     * Test whether the CPUView and its frame are initialized properly.
     */
    @Test
    public void testInitialization() {
        assertNotNull(cpuView.getFrame());
        assertTrue(cpuView.getFrame().isVisible());
    }

    /**
     * Test the behavior of the fetch details button click event.
     */
    @Test
    public void testFetchDetailsButtonListener() {
        ActionListenerStub listenerStub = new ActionListenerStub();
        cpuView.addFetchDetailsButtonListener(listenerStub);
        // Simulating button click
        listenerStub.actionPerformed(actionEvent);
        assertTrue(listenerStub.isActionPerformedCalled());
    }

    /**
     * Test the setup of a JTable with CPU model data.
     */
    @Test
    public void testSetupTable() {
        List<CPUModel> cpuModels = new ArrayList<>();

        // Add CPU models to the list
        cpuModels.add(new CPUModel("00012", "i7-13700l", "3.8GHz", "Y"));
        cpuModels.add(new CPUModel("00013", "R9 7900X", "3.3GHz", "Y"));
        cpuModels.add(new CPUModel("00014", "i3-12100F", "3.0GHz", "Y"));
        cpuModels.add(new CPUModel("00015", "R5 5600", "3.7GHz", "N"));
        cpuModels.add(new CPUModel("00016", "R7 7800X3D", "4.0GHz", "Y"));

        cpuView.setupTable(cpuModels);

        // Retrieve the JTable and perform assertions on its content
        JTable table = cpuView.getCPUTable();
        assertEquals(5, table.getRowCount());

        // Assertions for the first row
        assertEquals("00012", table.getValueAt(0, 0));
        assertEquals("i7-13700l", table.getValueAt(0, 1));

        // Assertions for the second row
        assertEquals("00013", table.getValueAt(1, 0));
        assertEquals("R9 7900X", table.getValueAt(1, 1));

        // Assertions for the third row
        assertEquals("00014", table.getValueAt(2, 0));
        assertEquals("i3-12100F", table.getValueAt(2, 1));

        // Assertions for the fourth row
        assertEquals("00015", table.getValueAt(3, 0));
        assertEquals("R5 5600", table.getValueAt(3, 1));

        // Assertions for the fifth row
        assertEquals("00016", table.getValueAt(4, 0));
        assertEquals("R7 7800X3D", table.getValueAt(4, 1));
    }

    // Stub classes for simulating GUI interactions:
    // MainViewStub simulates a main view with buttons.
    class MainViewStub extends MainView {

        private boolean gpuButtonClicked = false;
        private boolean cpuButtonClicked = false;

        @Override
        public void addGPUButtonListener(ActionListener listenForGPUButton) {
            getGpuButton().addActionListener(e -> gpuButtonClicked = true);
        }

        @Override
        public void addCPUButtonListener(ActionListener listenForCPUButton) {
            getCpuButton().addActionListener(e -> cpuButtonClicked = true);
        }

        public boolean wasGPUButtonClicked() {
            return gpuButtonClicked;
        }

        public boolean wasCPUButtonClicked() {
            return cpuButtonClicked;
        }
    }

    // ActionListenerStub simulates an action listener for testing.
    class ActionListenerStub implements java.awt.event.ActionListener {

        private boolean actionPerformedCalled = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            actionPerformedCalled = true;
        }

        public boolean isActionPerformedCalled() {
            return actionPerformedCalled;
        }
    }
}
