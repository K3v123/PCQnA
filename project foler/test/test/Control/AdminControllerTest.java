/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Control;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import view.AdminView;
import view.MainView;

/**
 *
 * @author kq635
 */
public class AdminControllerTest {
    // Test instances

    private AdminController adminController;  // Controller instance to be tested
    private MockAdminView adminView;          // Mocked version of the AdminView
    private MainView mainView;                // Actual MainView instance for testing

    /**
     * Mock class for AdminView to simulate real AdminView behavior without
     * involving the actual GUI components.
     */
    class MockAdminView extends AdminView {

        private String mockUsername;  // Mocked username for testing
        private String mockPassword;  // Mocked password for testing

        /**
         * Constructor to set the MainView object.
         *
         * @param mainView The main view instance.
         */
        public MockAdminView(MainView mainView) {
            super(mainView);
        }

        /**
         * Overridden getUsername method to return the mock username.
         *
         * @return The mocked username.
         */
        @Override
        public String getUsername() {
            return mockUsername;
        }

        /**
         * Overridden getPassword method to return the mock password.
         *
         * @return The mocked password.
         */
        @Override
        public String getPassword() {
            return mockPassword;
        }

        /**
         * Method to set the mock username.
         *
         * @param username Mocked username.
         */
        public void setUsername(String username) {
            this.mockUsername = username;
        }

        /**
         * Method to set the mock password.
         *
         * @param password Mocked password.
         */
        public void setPassword(String password) {
            this.mockPassword = password;
        }
    }

    /**
     * Setup method that runs before each test. It initializes the test
     * instances.
     */
    @Before
    public void setUp() {
        mainView = new MainView();
        adminView = new MockAdminView(mainView);
        adminController = new AdminController(adminView, mainView);
    }

    /**
     * Test to verify that an admin can log in with valid credentials.
     */
    @Test
    public void testValidAdminLogin() {
        // Setup: Provide mock credentials that are valid
        adminView.setUsername("pdc");
        adminView.setPassword("pdc");

        // Simulate the login button click
        adminController.new LoginListener().actionPerformed(null);

        // Assert: Check if the admin is logged in with the valid credentials
        assertTrue("Expected admin to be logged in with valid credentials", adminController.isAdminLoggedIn());
    }
}
