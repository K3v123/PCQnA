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
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
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

        // Set BoxLayout for main panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        motherboardHeader = new JLabel("Motherboard type:");
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

        panel.add(Box.createVerticalStrut(50));  // Create space after the go back button

        // Description
        descriptionText = new JTextArea("The motherboard is the main printed circuit board (PCB) in a computer. It holds many of the crucial components of the system, provides connectors for other peripherals, and communicates with other parts of the system.");
        setupTextArea(descriptionText);
        panel.add(descriptionText);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(motherboardHeader);
        panel.add(headerPanel);
        // Type and Size labels
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(infoPanel);

        // Buttons
        recommendationButton = new JButton("Recommendation");
        recommendationButton.addActionListener(e -> showRecommendation());
        fetchDetailsButton = new JButton("Fetch Details");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(fetchDetailsButton);
        buttonPanel.add(Box.createHorizontalStrut(10));  // Add horizontal space between buttons
        buttonPanel.add(recommendationButton);
        panel.add(buttonPanel);

        frame.add(panel);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                goBackButton.doClick(); // Simulate a click on the go back button when closing the window
            }
        });
    }

    // Displays a recommendation for motherboards and allows users to close the recommendation dialog.
    private void showRecommendation() {
        frame.setVisible(false);

        recommendationDialog = new JDialog(frame, "Recommendation", true);
        recommendationDialog.setSize(450, 250);
        JTextArea recommendationText = new JTextArea("For most users, a motherboard from ASUS, MSI, or Gigabyte with compatibility for the user's desired CPU and RAM will be sufficient. Make sure to check for compatibility.");
        setupTextArea(recommendationText);
        JScrollPane recommendationScrollPane = new JScrollPane(recommendationText);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            recommendationDialog.dispose();
            frame.setVisible(true);
        });

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(recommendationScrollPane);
        dialogPanel.add(closeButton);

        recommendationDialog.add(dialogPanel);
        recommendationDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        recommendationDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                recommendationDialog.dispose();
                frame.setVisible(true);
            }
        });
        recommendationDialog.setVisible(true);
    }

    // Sets up the appearance and behavior of a JTextArea for displaying text.
    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
    }

    // Adds an ActionListener to the "Fetch Details" button.
    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    // Closes the MotherboardView frame.
    public void dispose() {
        frame.dispose();
    }

    // Sets up the JTable to display a list of motherboard details.
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

    // Displays an error message in a dialog.
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    // Retrieves the "Fetch Details" button.
    public JButton getFetchDetailsButton() {
        return fetchDetailsButton;
    }

}
