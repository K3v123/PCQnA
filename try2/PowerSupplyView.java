/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;

// Represents the view for Power Supply Unit (PSU) details. Extends BaseView.
public class PowerSupplyView extends BaseView {

    private JLabel powerClassHeader;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;
    private JTable powerSupplyTable;
    private boolean fetched = false;
    private MainView mainView;  // Assuming your main view is named "MainView"

    // Constructs a PowerSupplyView with a reference to the main view.
    public PowerSupplyView(MainView mainView) {
        super();

        this.mainView = mainView;  // Initialize the main view reference

        initializeFrame("Power Supply Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        powerClassHeader = new JLabel("Power Class: ");
        infoText = new JTextArea("A power supply unit converts power from the wall outlet to the type that the computer components need.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch Power Supply Details");
        recommendationButton = new JButton("Show Recommendation");

        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(Box.createVerticalStrut(40));  // Adding vertical space
        panel.add(infoText);
        panel.add(powerClassHeader);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);

        // Modify the goBackButton action
        goBackButton.addActionListener(e -> {
            frame.setVisible(false); // Hide the current frame
            mainView.setVisible(true);  // Show the main view
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                mainView.setVisible(true);  // Show the main view
            }
        });
    }

    // Adds a listener to the fetchDetailsButton. The listener is executed when the button is clicked.
    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fetched) { // Check if the button hasn't been pressed before
                    listener.actionPerformed(e); // Execute the listener
                    fetched = true; // Update the fetched state
                    fetchDetailsButton.setEnabled(false); // Disable the button
                }
            }
        });
    }

    // Sets the power supply details to display in the view.
    public void setPowerSupplyDetails(String powerRating) {
        powerClassHeader.setText("Power Rating: " + powerRating);
    }

    // Shows a recommendation dialog.
    private void showRecommendation() {
        recommendationFrame = new JFrame("Power Supply Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        recommendationFrame.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                recommendationFrame.dispose();
                frame.setVisible(true);
            }
        });

        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

        recommendationText = new JTextArea("We recommend at least 500W for a basic setup and higher for gaming or workstation setups.", 5, 30);
        recommendationText.setEditable(false);
        recommendationText.setWrapStyleWord(true);
        recommendationText.setLineWrap(true);

        JButton goBackFromRecommendation = new JButton("Go Back");
        goBackFromRecommendation.addActionListener(e -> {
            recommendationFrame.dispose();
            frame.setVisible(true);
        });

        recommendationPanel.add(recommendationText);
        recommendationPanel.add(goBackFromRecommendation);
        recommendationFrame.add(recommendationPanel);
        recommendationFrame.setVisible(true);
        frame.setVisible(false);
    }

    // Sets up a table to display Power Supply Unit (PSU) details.
    public void setupTable(List<PowerSupplyModel> powerSupplyList) {
        String[] columnNames = {"ID", "Class"};
        Object[][] data = new Object[powerSupplyList.size()][2];
        for (int i = 0; i < powerSupplyList.size(); i++) {
            data[i][0] = powerSupplyList.get(i).getId();
            data[i][1] = powerSupplyList.get(i).getPowerClass();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        powerSupplyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(powerSupplyTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

    // Closes the PowerSupplyView window.
    public void closeWindow() {
        frame.dispose();
    }

}
