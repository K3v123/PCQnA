/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Control;

import Base.DatabaseManager;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import view.MainView;
import view.MostAskedView;

/**
 *
 * @author kq635
 */
/**
 * Test suite for the MostAskedController class.
 * It focuses on verifying interactions between the controller and its associated views and database.
 */
public class MostAskedControllerTest {
    // Fake or stub versions of the views and database for controlled testing

    private FakeMostAskedView fakeMostAskedView;
    private FakeMainView fakeMainView;
    private FakeDatabaseManager fakeDbManager;

    // Instance of the controller under test
    private MostAskedController controller;

    /**
     * Setup method to initialize the test environment before each test. This
     * involves setting up fake views and a fake database, and injecting them
     * into the controller.
     */
    @Before
    public void setUp() {
        fakeMostAskedView = new FakeMostAskedView();
        fakeMainView = new FakeMainView();
        fakeDbManager = new FakeDatabaseManager();

        controller = new MostAskedController(fakeMostAskedView, fakeMainView);
        controller.dbManager = fakeDbManager;
    }

    /**
     * Test the populateMostAskedData method of the controller. Verifies that
     * data fetched from the database is correctly displayed in the view.
     */
    @Test
    public void testPopulateMostAskedData() {
        List<String> dummyData = Arrays.asList("Component1", "Component2", "Component3");
        fakeDbManager.setMostAskedComponents(dummyData);

        controller.populateMostAskedData();

        assertTrue(fakeMostAskedView.displayedOptions.equals(dummyData));
    }

    /**
     * Fake implementation of the MostAskedView for testing. It captures the
     * displayed options without rendering an actual GUI.
     */
    private class FakeMostAskedView extends MostAskedView {

        List<String> displayedOptions;

        @Override
        public void displayMostAskedOptions(List<String> options) {
            this.displayedOptions = options;
        }
    }

    /**
     * Fake implementation of the MainView for testing. It captures the
     * visibility state of the frame without rendering an actual GUI.
     */
    private class FakeMainView extends MainView {

        boolean frameVisibility = false;

        @Override
        public JFrame getFrame() {
            return new JFrame() {
                @Override
                public void setVisible(boolean b) {
                    frameVisibility = b;
                }

                @Override
                public boolean isVisible() {
                    return frameVisibility;
                }
            };
        }
    }

    /**
     * Fake implementation of the DatabaseManager for testing. Allows setting a
     * fixed list of "most asked components" for controlled testing.
     */
    private class FakeDatabaseManager extends DatabaseManager {

        private List<String> mostAskedComponents;

        @Override
        public List<String> getMostAskedComponents() {
            return mostAskedComponents;
        }

        public void setMostAskedComponents(List<String> components) {
            this.mostAskedComponents = components;
        }
    }
}
