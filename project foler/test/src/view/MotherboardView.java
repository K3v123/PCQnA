/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import Model.MotherboardModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MotherboardView extends BaseView {

    private JTable motherboardTable = new JTable();
    private JLabel motherboardHeader;
    private JTextArea descriptionText;
    private JButton fetchDetailsButton, recommendationButton;
    private JDialog recommendationDialog;

    public MotherboardView() {
        super();
        initializeFrame("Motherboard Details");

        // Set BoxLayout for the main panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a label for motherboard type
        motherboardHeader = new JLabel("Motherboard type:");

        // Create a button for going back
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        // Add space after the go back button
        panel.add(Box.createVerticalStrut(50));

        // Create a JTextArea for displaying motherboard description
        descriptionText = new JTextArea("The motherboard is the main printed circuit board (PCB) in a computer. It holds many of the crucial components of the system, provides connectors for other peripherals, and communicates with other parts of the system.");
        setupTextArea(descriptionText);
        panel.add(descriptionText);

        // Create a panel for the header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(motherboardHeader);
        panel.add(headerPanel);

        // Create a panel for additional information (not provided in the code)
        // Create buttons for fetching details and showing recommendations
        recommendationButton = new JButton("Recommendation");
        recommendationButton.addActionListener(e -> showRecommendation());
        fetchDetailsButton = new JButton("Fetch Details");

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(fetchDetailsButton);
        buttonPanel.add(Box.createHorizontalStrut(10));  // Add horizontal space between buttons
        buttonPanel.add(recommendationButton);
        panel.add(buttonPanel);

        // Add the main panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);

        // Add a window listener to handle window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                goBackButton.doClick(); // Simulate a click on the go back button when closing the window
            }
        });
    }

    // Method to show a recommendation dialog
    private void showRecommendation() {
        frame.setVisible(false);

        // Create a recommendation dialog
        recommendationDialog = new JDialog(frame, "Recommendation", true);
        recommendationDialog.setSize(450, 250);

        // Create a JTextArea for displaying the recommendation
        JTextArea recommendationText = new JTextArea("For most users, a motherboard from ASUS, MSI, or Gigabyte with compatibility for the user's desired CPU and RAM will be sufficient. Make sure to check for compatibility.");
        setupTextArea(recommendationText);
        JScrollPane recommendationScrollPane = new JScrollPane(recommendationText);

        // Create a close button for the recommendation dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            recommendationDialog.dispose();
            frame.setVisible(true);
        });

        // Create a panel for the recommendation dialog
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(recommendationScrollPane);
        dialogPanel.add(closeButton);

        // Add components to the recommendation dialog
        recommendationDialog.add(dialogPanel);

        // Set behavior for closing the recommendation dialog
        recommendationDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        recommendationDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                recommendationDialog.dispose();
                frame.setVisible(true);
            }
        });

        // Make the recommendation dialog visible
        recommendationDialog.setVisible(true);
    }

    // Helper method to set up a JTextArea
    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
    }

    // Method to add a listener to the fetch details button
    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    // Method to dispose of the view
    public void dispose() {
        frame.dispose();
    }

    // Method to set up the motherboard table with data
    public void setupMotherboardTable(List<MotherboardModel> motherboardList) {
        String[] columnNames = {"ID", "Type", "Size"};
        Object[][] data = new Object[motherboardList.size()][3];
        for (int i = 0; i < motherboardList.size(); i++) {
            data[i][0] = motherboardList.get(i).getId();
            data[i][1] = motherboardList.get(i).getType();
            data[i][2] = motherboardList.get(i).getSize();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        motherboardTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(motherboardTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

    // Method to display an error message using a JOptionPane
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    // Getter for the fetch details button
    public JButton getFetchDetailsButton() {
        return fetchDetailsButton;
    }
}
