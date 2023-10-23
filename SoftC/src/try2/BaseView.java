/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kq635
 */
public class BaseView {

    protected JFrame frame;
    protected JPanel panel;
    protected JButton goBackButton;

    public BaseView() {
        frame = new JFrame();
        panel = new JPanel();
        goBackButton = new JButton("Go Back");
    }

    protected void initializeFrame(String title) {
        frame.setTitle(title);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addGoBackButtonListener(ActionListener listenForGoBackButton) {
        goBackButton.addActionListener(listenForGoBackButton);
    }

    public void dispose() {
        frame.dispose();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
}
