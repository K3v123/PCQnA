/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BaseView {

    // Declare frame, panel, and "Go Back" button components.
    public JFrame frame;
    protected JPanel panel;
    protected JButton goBackButton;

    /**
     * Constructor for the `BaseView` class. Initializes the frame, panel, and
     * "Go Back" button.
     */
    public BaseView() {
        frame = new JFrame();
        panel = new JPanel();
        goBackButton = new JButton("Go Back");
    }

    /**
     * Initializes the frame with a specified title and default size.
     *
     * @param title The title of the frame.
     */
    protected void initializeFrame(String title) {
        frame.setTitle(title);
        frame.setSize(600, 400);  // Default size for the frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * Adds an action listener to the "Go Back" button.
     *
     * @param listenForGoBackButton The action listener for the button.
     */
    public void addGoBackButtonListener(ActionListener listenForGoBackButton) {
        goBackButton.addActionListener(listenForGoBackButton);
    }

    /**
     * Utility method to set the bounds (position and size) of a Swing
     * component.
     *
     * @param component The component to set the bounds for.
     * @param x The x-coordinate of the top-left corner of the component.
     * @param y The y-coordinate of the top-left corner of the component.
     * @param width The width of the component.
     * @param height The height of the component.
     */
    protected void setComponentBounds(JComponent component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
    }

    /**
     * Utility method to add vertical space (strut) to a panel.
     *
     * @param targetPanel The panel to which vertical space is added.
     * @param space The amount of vertical space to add.
     */
    protected void addVerticalSpace(JPanel targetPanel, int space) {
        targetPanel.add(Box.createVerticalStrut(space));
    }
}
