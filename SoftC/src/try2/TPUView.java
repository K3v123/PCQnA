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

public class TPUView extends BaseView {

    private JLabel speedLabel;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText, infoText;

    public TPUView() {
        super();
        initializeFrame("TPU Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        speedLabel = new JLabel("Speed: ");
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

        panel.add(infoText);
        panel.add(speedLabel);
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void setTPUDetails(String speed) {
        speedLabel.setText("Speed: " + speed);
    }

    private void showRecommendation() {
        recommendationFrame = new JFrame("TPU Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

        recommendationText = new JTextArea("TPUs are recommended for AI tasks, not general computing. Ideal for people interested in AI.", 5, 30);
        recommendationText.setEditable(false);
        recommendationText.setWrapStyleWord(true);
        recommendationText.setLineWrap(true);

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
        recommendationFrame.setVisible(true);
        frame.setVisible(false);
    }
}
