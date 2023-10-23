/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package view;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kq635
 */
public class AdminViewTest {

    private AdminView adminView;
    private MainView mainView;

    @Before
    public void setUp() {
        mainView = new MainView();
        adminView = new AdminView(mainView);
    }

    @Test
    public void testGetUsername() {
        adminView.getUsernameField().setText("testUsername");
        assertEquals("Expected username to be 'testUsername'", "testUsername", adminView.getUsername());
    }

    @Test
    public void testGetPassword() {
        adminView.setPassword("testPassword");
        assertEquals("Expected password to be 'testPassword'", "testPassword", adminView.getPassword());
    }

    @Test
    public void testIsGPUVisible() {
        adminView.getGpuCheckBox().setSelected(true);
        assertTrue("Expected GPU to be visible", adminView.isGPUVisible());
    }

    @Test
    public void testIsCPUVisible() {
        adminView.getCpuCheckBox().setSelected(true);
        assertTrue("Expected CPU to be visible", adminView.isCPUVisible());
    }

    @Test
    public void testIsMemoryVisible() {
        adminView.getMemoryCheckBox().setSelected(true);
        assertTrue("Expected Memory to be visible", adminView.isMemoryVisible());
    }

    @Test
    public void testIsPowerSupplyVisible() {
        adminView.getPowerSupplyCheckBox().setSelected(true);
        assertTrue("Expected PowerSupply to be visible", adminView.isPowerSupplyVisible());
    }

    @Test
    public void testIsCoolingVisible() {
        adminView.getCoolingCheckBox().setSelected(true);
        assertTrue("Expected Cooling to be visible", adminView.isCoolingVisible());
    }

    @Test
    public void testIsStorageVisible() {
        adminView.getStorageCheckBox().setSelected(true);
        assertTrue("Expected Storage to be visible", adminView.isStorageVisible());
    }

    @Test
    public void testIsTPUVisible() {
        adminView.getTpuCheckBox().setSelected(true);
        assertTrue("Expected TPU to be visible", adminView.isTPUVisible());
    }

    @Test
    public void testIsMotherboardVisible() {
        adminView.getMotherboardCheckBox().setSelected(true);
        assertTrue("Expected Motherboard to be visible", adminView.isMotherboardVisible());
    }

    @Test
    public void testSaveAndLoadVisibilityState() {
        // Set a specific state
        adminView.getGpuCheckBox().setSelected(true);
        adminView.getCpuCheckBox().setSelected(false);

        // Save the state
        adminView.saveVisibilityState();

        // Change the state
        adminView.getGpuCheckBox().setSelected(false);
        adminView.getCpuCheckBox().setSelected(true);

        // Load the saved state
        adminView.loadVisibilityState();

        // Check if the state is as saved
        assertTrue("Expected GPU to be visible after loading state", adminView.isGPUVisible());
        assertFalse("Expected CPU to be invisible after loading state", adminView.isCPUVisible());
    }
}
