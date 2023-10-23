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
/**
 * JUnit4 Test Suite for the MainView class. This suite tests basic
 * functionalities of the MainView, including frame initialization, visibility
 * toggling, and error message display.
 */
public class MainViewTest {

    // Instance of the MainView which will be tested
    private MainView mainView;

    /**
     * Setup method to instantiate the MainView before each test.
     */
    @Before
    public void setUp() {
        mainView = new MainView();
    }

    /**
     * Test to ensure the frame is not null after initialization.
     */
    @Test
    public void testFrameNotNullAfterInitialization() {
        assertNotNull("Frame should not be null after initialization", mainView.getFrame());
    }

    /**
     * Test to ensure the frame's title is set correctly after initialization.
     */
    @Test
    public void testFrameTitleAfterInitialization() {
        assertEquals("Expected frame title to be 'PC Builder Guide'", "PC Builder Guide", mainView.getFrame().getTitle());
    }

    /**
     * Test to ensure the visibility of the MainView can be set and retrieved
     * correctly.
     */
    @Test
    public void testSetAndGetVisibility() {
        mainView.setVisible(true);
        assertTrue(mainView.getFrame().isVisible());

        mainView.setVisible(false);
        assertFalse(mainView.getFrame().isVisible());
    }

    /**
     * Test to ensure error messages can be displayed without exceptions. Note:
     * In a real-world scenario, this might use mocking or other techniques to
     * validate the message.
     */
    @Test
    public void testDisplayErrorMessage() {

        mainView.displayErrorMessage("Test error message.");
    }
}
