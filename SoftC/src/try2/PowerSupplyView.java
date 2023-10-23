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

public class PowerSupplyView extends BaseView {

    private JLabel powerRatingLabel;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;

    public PowerSupplyView() {
        super();
        initializeFrame("Power Supply Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        powerRatingLabel = new JLabel("Power Rating: ");
        infoText = new JTextArea("A power supply unit converts power from the wall outlet to the type that the computer components need.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch Power Supply Details");
        recommendationButton = new JButton("Show Recommendation");

        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(infoText);
        panel.add(powerRatingLabel);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void setPowerSupplyDetails(String powerRating) {
        powerRatingLabel.setText("Power Rating: " + powerRating);
    }

    private void showRecommendation() {
        recommendationFrame = new JFrame("Power Supply Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
}
