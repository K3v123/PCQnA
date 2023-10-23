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

public class StorageView extends BaseView {

    private JLabel typeLabel, sizeLabel, speedLabel;
    private JTextArea infoText;
    private JButton fetchDetailsButton, recommendationButton;
    private JFrame recommendationFrame;
    private JTextArea recommendationText;

    public StorageView() {
        super();
        initializeFrame("Storage Details");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Brief Information
        infoText = new JTextArea("Storage devices are used to store and retrieve data. They come in different forms like HDD, SSD, etc.", 5, 30);
        infoText.setEditable(false);
        infoText.setWrapStyleWord(true);
        infoText.setLineWrap(true);

        typeLabel = new JLabel("Type: ");
        sizeLabel = new JLabel("Size: ");
        speedLabel = new JLabel("Speed: ");

        fetchDetailsButton = new JButton("Fetch Storage Details");
        recommendationButton = new JButton("Show Recommendation");
        recommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRecommendation();
            }
        });

        panel.add(infoText);
        panel.add(typeLabel);
        panel.add(sizeLabel);
        panel.add(speedLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(fetchDetailsButton);
        panel.add(recommendationButton);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void addFetchDetailsButtonListener(ActionListener listener) {
        fetchDetailsButton.addActionListener(listener);
    }

    public void setStorageDetails(String type, String size, String speed) {
        typeLabel.setText("Type: " + type);
        sizeLabel.setText("Size: " + size);
        speedLabel.setText("Speed: " + speed);
    }

    private void showRecommendation() {
        recommendationFrame = new JFrame("Storage Recommendation");
        recommendationFrame.setSize(300, 200);
        recommendationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));

        recommendationText = new JTextArea("Recommended storage for new users is SSD due to its speed. HDD is cheaper but slower.", 5, 30);
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
