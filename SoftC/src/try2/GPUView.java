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

public class GPUView extends BaseView {

    private JLabel nameLabel, speedLabel, classLabel;
    private JTextArea recommendationText, descriptionText;
    private JButton fetchTypesButton, recommendationButton;
    private JDialog recommendationDialog;

    public GPUView() {
        super();

        frame.setTitle("GPU Details");
        frame.setSize(400, 300);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Description
        descriptionText = new JTextArea("A GPU, or Graphics Processing Unit, is a specialized electronic circuit designed to accelerate the image output in a frame buffer intended for output to a display.");
        setupTextArea(descriptionText);
        panel.add(descriptionText);

        // GPU Details
        nameLabel = new JLabel("Name: ");
        speedLabel = new JLabel("Speed: ");
        classLabel = new JLabel("Class: ");

        fetchTypesButton = new JButton("Fetch GPU Types");
        recommendationButton = new JButton("View Recommendation");
        recommendationButton.addActionListener(e -> showRecommendation());

        // Add components to panel
        panel.add(nameLabel);
        panel.add(speedLabel);
        panel.add(classLabel);
        panel.add(fetchTypesButton);
        panel.add(recommendationButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void setupTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void showRecommendation() {
        frame.setVisible(false);

        recommendationDialog = new JDialog(frame, "Recommendation", true);
        recommendationDialog.setSize(400, 200);

        recommendationText = new JTextArea("For gaming, we recommend NVIDIA's RTX series or AMD's RX series. For graphic design, NVIDIA's Quadro series or AMD's Radeon Pro series is suitable.");
        setupTextArea(recommendationText);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            recommendationDialog.dispose();
            frame.setVisible(true);
        });

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.add(recommendationText);
        dialogPanel.add(closeButton);

        recommendationDialog.add(dialogPanel);
        recommendationDialog.setVisible(true);
    }

    public void setGPUDetails(String name, String speed, String gpuClass) {
        nameLabel.setText("Name: " + name);
        speedLabel.setText("Speed: " + speed);
        classLabel.setText("Class: " + gpuClass);
    }

    public void addFetchTypesButtonListener(ActionListener listenForFetchTypesButton) {
        fetchTypesButton.addActionListener(listenForFetchTypesButton);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    public void closeWindow() {
        frame.dispose();
    }
}
