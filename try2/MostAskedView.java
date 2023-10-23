/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MostAskedView extends BaseView {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton goBackButton;
    private JPanel buttonPanel;

    // Retrieves the frame associated with this view.
    public JFrame getFrame() {
        return frame;
    }

    // Initializes the MostAskedView with a JFrame and sets up its components.
    public MostAskedView() {
        frame = new JFrame("Most Asked Components");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();  // Close the MostAskedView
                new MainView().getFrame().setVisible(true);  // Show the MainView
            }
        });

        frame.setSize(500, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();  // Modified this line
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        frame.add(mainPanel);
        goBackButton = new JButton("Go Back");
        buttonPanel.add(goBackButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Adding action for go back button
    public void addGoBackButtonListener(ActionListener listener) {
        goBackButton.addActionListener(listener);
    }

    public void displayMostAskedOptions(List<String> options) {
        // Clear any previous content
        mainPanel.removeAll();

        // Create optionsPanel outside the loop
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
   
        // Add each option to the panel
        for (String option : options) {
            JLabel optionLabel = new JLabel(option);
            optionsPanel.add(optionLabel);
        }

        // Add the options to the center
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        // Add the 'Go Back' button again
        buttonPanel.add(goBackButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Refresh the panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
