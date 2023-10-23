/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import Control.MainController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;

public class MostAskedView extends BaseView {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton goBackButton;
    private JPanel buttonPanel;

    public JFrame getFrame() {
        return frame;
    }

    public MostAskedView() {
        frame = new JFrame("Most Asked Components");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a window listener to handle window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();  // Close the MostAskedView
                new MainController();  // Create a new MainController which will create and show the MainView
            }
        });

        frame.setSize(500, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        frame.add(mainPanel);
        goBackButton = new JButton("Go Back");
        goBackButton.setName("Go Back"); // for juinttest
        buttonPanel.add(goBackButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Add an ActionListener for the "Go Back" button
    public void addGoBackButtonListener(ActionListener listener) {
        goBackButton.addActionListener(listener);
    }

    // Display a list of most asked options
    public void displayMostAskedOptions(List<String> options) {
        // Clear any previous content
        mainPanel.removeAll();

        // Create an optionsPanel to hold the list of options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        // Add each option to the panel
        for (String option : options) {
            JLabel optionLabel = new JLabel(option);
            optionLabel.setName(option); // for juint
            optionsPanel.add(optionLabel);
        }

        // Add the optionsPanel to the center of the main panel
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        // Add the 'Go Back' button again to the bottom
        buttonPanel.add(goBackButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Refresh the panel to reflect the changes
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Getter for the 'Go Back' button (for unit testing)
    public JButton getGoBackButton() {
        return goBackButton;
    }

}
