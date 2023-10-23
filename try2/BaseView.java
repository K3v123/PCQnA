/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kq635
 */


// BaseView class provides a common base for creating Swing-based views.
public class BaseView {

    // Attributes for frame, panel, and goBackButton
    protected JFrame frame;
    protected JPanel panel;
    protected JButton goBackButton;

    // Constructor to initialize the frame, panel, and goBackButton
    public BaseView() {
        frame = new JFrame();
        panel = new JPanel();
        goBackButton = new JButton("Go Back");
    }

    // Initialize the frame with a specified title
    protected void initializeFrame(String title) {
        frame.setTitle(title);
        frame.setSize(600, 400); // Adjusted size
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Adjusted close behavior
    }

    // Add a listener for the "Go Back" button
    public void addGoBackButtonListener(ActionListener listenForGoBackButton) {
        goBackButton.addActionListener(listenForGoBackButton);
    }

    // Dispose of the view's frame
    public void dispose() {
        frame.dispose();
    }

    // Display an error message using a dialog
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
}
