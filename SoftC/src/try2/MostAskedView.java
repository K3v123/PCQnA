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

public class MostAskedView extends BaseView {

    private JTextArea mostAskedText;

    public MostAskedView() {
        super();
        initializeFrame("Most Asked Components");

        mostAskedText = new JTextArea(10, 30);
        mostAskedText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(mostAskedText);

        panel.add(new JLabel("Top Chosen Components:"));
        panel.add(scrollPane);
        panel.add(goBackButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setMostAskedData(String data) {
        mostAskedText.setText(data);
    }
}
