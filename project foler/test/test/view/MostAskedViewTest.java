/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package view;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kq635
 */
/**
 * Test class for MostAskedView.
 */
public class MostAskedViewTest {

    // The view instance under test
    private MostAskedView view;

    // Set up method to initialize the view before each test
    @Before
    public void setUp() {
        view = new MostAskedView();
    }

    // Test to ensure the frame is properly initialized
    @Test
    public void testGetFrame() {
        assertNotNull("Frame should not be null", view.getFrame());
    }

    // Test to verify that the most asked options are displayed correctly
    @Test
    public void testDisplayMostAskedOptions() throws Exception {
        List<String> dummyData = getLabelFieldNamesFromClass();
        MostAskedView viewInstance = new MostAskedView();

        SwingUtilities.invokeAndWait(() -> {
            viewInstance.displayMostAskedOptions(dummyData);

            for (String option : dummyData) {
                Component foundComponent = getComponentByName(viewInstance.getFrame(), option);
                assertNotNull("Option should be displayed", foundComponent);
                assertTrue(foundComponent instanceof JLabel);
                assertEquals(option, ((JLabel) foundComponent).getText());
            }
        });
    }

    // Utility method to fetch the names of JLabel fields from the MostAskedView class
    private List<String> getLabelFieldNamesFromClass() {
        return Arrays.stream(MostAskedView.class.getDeclaredFields())
                .filter(field -> JLabel.class.isAssignableFrom(field.getType()))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    // Test to verify that the "Go Back" button is initialized correctly
    @Test
    public void testAddGoBackButtonListener() throws Exception {
        MostAskedView viewInstance = new MostAskedView();

        try {
            SwingUtilities.invokeAndWait(() -> {
                JButton goBackButton = viewInstance.getGoBackButton();
                assertNotNull("Go Back button should not be null", goBackButton);
                assertEquals("Go Back", goBackButton.getText());
            });
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
            Throwable cause = ite.getCause();
            if (cause != null) {
                cause.printStackTrace();
            }
        }
    }

    // Utility method to search for a component by name within a container
    private Component getComponentByName(Container container, String name) {
        for (Component comp : container.getComponents()) {
            if (name.equals(comp.getName())) {
                return comp;
            }
            if (comp instanceof JPanel) {
                Component innerComp = getComponentByName((JPanel) comp, name);
                if (innerComp != null) {
                    return innerComp;
                }
            }
        }
        return null;
    }

}
