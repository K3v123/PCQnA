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

    private JLabel nameLabel, speedLabel, classificationLabel;
    private JTextArea recommendationText;
    private JButton fetchTypesButton;

    public GPUView() {
        super();

        frame.setTitle("GPU Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // GPU Details
        nameLabel = new JLabel("Name: ");
        speedLabel = new JLabel("Speed: ");
        classificationLabel = new JLabel("Classification: ");

        recommendationText = new JTextArea(5, 30);
        recommendationText.setEditable(false);

        fetchTypesButton = new JButton("Fetch GPU Types");

        // Add components to panel
        panel.add(nameLabel);
        panel.add(speedLabel);
        panel.add(classificationLabel);
        panel.add(new JLabel("Recommendation:"));
        panel.add(recommendationText);
        panel.add(fetchTypesButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setGPUDetails(String name, String speed, String classification) {
        nameLabel.setText("Name: " + name);
        speedLabel.setText("Speed: " + speed);
        classificationLabel.setText("Classification: " + classification);
    }

    public void setRecommendation(String recommendation) {
        recommendationText.setText(recommendation);
    }

    public void addFetchTypesButtonListener(ActionListener listenForFetchTypesButton) {
        fetchTypesButton.addActionListener(listenForFetchTypesButton);
    }

    
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }

    public void closeWindow() {
        frame.dispose();  // Close the current window
    }

}
