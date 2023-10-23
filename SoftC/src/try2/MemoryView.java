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

public class MemoryView extends BaseView {

    private JLabel sizeLabel;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;

    public MemoryView() {
        super();
        initializeFrame("Memory Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        sizeLabel = new JLabel("Size: ");
        infoText = new JTextArea("Memory (RAM) is the primary storage used for a computer's immediate tasks.", 5, 30);
        infoText.setEditable(false);
        fetchDetailsButton = new JButton("Fetch Memory Details");
        recommendationButton = new JButton("Show Recommendation");

        recommendationButton.addActionListener(e -> showRecommendation());

        panel.add(infoText);
        panel.add(sizeLabel);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void setMemoryDetails(String size) {
        sizeLabel.setText("Size: " + size);
    }

    private void showRecommendation() {
        recommendationFrame = new JFrame("Memory Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

        recommendationText = new JTextArea("8GB is the minimum for most tasks, 16GB is recommended for gaming & professional work.", 5, 30);
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
