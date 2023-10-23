/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Control.MotherboardController;
import Model.MotherboardModel;
import view.MainView;
import view.MotherboardView;

/**
 * This class handles the button click event for the Motherboard button in the
 * main view.
 *
 * @author kq635
 */
public class MotherboardButtonHandler {

    private MainView mainView;

    public MotherboardButtonHandler(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Handles the button click event by creating a new MotherboardController
     * and hiding the main view.
     */
    public void handle() {
        // Create a new MotherboardController with the necessary components
        MotherboardController motherboardController = new MotherboardController(new MotherboardModel(), new MotherboardView(), mainView);

        // Hide the main view
        mainView.setVisible(false);
    }
}
