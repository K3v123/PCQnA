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

public class MotherboardView extends BaseView {

    private JLabel typeLabel, sizeLabel;
    private JTextArea descriptionText;
    private JButton fetchDetailsButton, recommendationButton;
    private JDialog recommendationDialog;

    public MotherboardView() {
        super();
        initializeFrame("Motherboard Details");

        // Set BorderLayout for main panel
        panel.setLayout(new BorderLayout());

        // Description with scroll pane
        descriptionText = new JTextArea(5, 20);
        descriptionText.setText("The motherboard is the main printed circuit board (PCB) in a computer. It holds many of the crucial components of the system, provides connectors for other peripherals, and communicates with other parts of the system.");
        setupTextArea(descriptionText);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionText);

        // Type and Size labels
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typeLabel = new JLabel("Type: ");
        sizeLabel = new JLabel("Size: ");
        infoPanel.add(typeLabel);
        infoPanel.add(sizeLabel);

        // Buttons
        recommendationButton = new JButton("Recommendation");
        recommendationButton.addActionListener(e -> showRecommendation());
        JPanel buttonPanel = new JPanel();
        fetchDetailsButton = new JButton("Fetch Details");
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(fetchDetailsButton);
        buttonPanel.add(recommendationButton);
        buttonPanel.add(goBackButton);

        // Add components to main panel
        panel.add(descriptionScrollPane, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showRecommendation() {
        frame.setVisible(false);

        recommendationDialog = new JDialog(frame, "Recommendation", true);
        recommendationDialog.setSize(450, 250);
        JTextArea recommendationText = new JTextArea(5, 30);
        recommendationText.setText("For most users, a motherboard from ASUS, MSI, or Gigabyte with compatibility for the user's desired CPU and RAM will be sufficient. Make sure to check for compatibility.");
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
        recommendationDialog.setVisible(true);
    }

    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
    }

    public void setMotherboardDetails(String type, String size) {
        typeLabel.setText("Type: " + type);
        sizeLabel.setText("Size: " + size);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void dispose() {
        frame.dispose();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
}
