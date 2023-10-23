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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TPUView extends BaseView {

    private JLabel TPUHeader;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;
    private JTable tpuTable;
    private boolean fetched = false;
    
    // Constructs a TPUView, initializing the user interface for TPU details.
    public TPUView() {
        super();
        initializeFrame("TPU Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        TPUHeader = new JLabel("TPU Types:");
        infoText = new JTextArea("TPUs are specialized hardware for AI computations.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch TPU Details");
        recommendationButton = new JButton("Show Recommendation");

        recommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRecommendation();
            }
        });

        panel.add(Box.createVerticalStrut(40));  // Adding vertical space
        panel.add(infoText);
        panel.add(TPUHeader);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        goBackButton.setText("<");
        goBackButton.setBounds(10, 10, 45, 30);
        frame.add(goBackButton);

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

    // Adds an ActionListener to the Fetch TPU Details button.
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

    // Displays a recommendation dialog for TPUs.
    private void showRecommendation() {
        recommendationFrame = new JFrame("TPU Recommendation");
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

        recommendationText = new JTextArea("TPUs are recommended for AI tasks, not general computing. Ideal for people interested in AI.", 5, 30);
        recommendationText.setEditable(false);
        recommendationText.setWrapStyleWord(true);
        recommendationText.setLineWrap(true);

        // For new goBackFromRecommendation button with its action
        JButton goBackFromRecommendation = new JButton("Go Back");
        goBackFromRecommendation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recommendationFrame.dispose();
                frame.setVisible(true);
            }
        });

        recommendationPanel.add(recommendationText);
        recommendationPanel.add(goBackFromRecommendation);
        recommendationFrame.add(recommendationPanel);
        // setting the visibilities
        recommendationFrame.setVisible(true);
        frame.setVisible(false);
    }

    // Sets up a table to display TPU details.
    public void setupTable(List<TPUModel> tpuList) {
        String[] columnNames = {"ID", "Type", "Speed"};
        Object[][] data = new Object[tpuList.size()][3];
        for (int i = 0; i < tpuList.size(); i++) {
            data[i][0] = tpuList.get(i).getId();
            data[i][1] = tpuList.get(i).getClassification();
            data[i][2] = tpuList.get(i).getSpeed();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tpuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tpuTable);
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }

}
