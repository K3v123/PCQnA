/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
import java.awt.event.ActionListener;
import javax.swing.*;

public class StorageView extends BaseView {

    private JLabel typeLabel, sizeLabel, speedLabel;
    private JButton fetchDetailsButton;

    public StorageView() {
        super();
        initializeFrame("Storage Details");

        typeLabel = new JLabel("Type: ");
        sizeLabel = new JLabel("Size: ");
        speedLabel = new JLabel("Speed: ");

        fetchDetailsButton = new JButton("Fetch Storage Details");

        panel.add(typeLabel);
        panel.add(sizeLabel);
        panel.add(speedLabel);
        panel.add(fetchDetailsButton); // Add the fetch details button to the panel
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
}
