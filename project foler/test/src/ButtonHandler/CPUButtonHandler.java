/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Control.CPUController;
import Model.CPUModel;
import view.CPUView;
import view.MainView;

/**
 * The `CPUButtonHandler` class is responsible for handling CPU-related actions.
 * It creates a `CPUController` instance, associates it with a CPU model and
 * view, and hides the main view. This class is typically used to manage
 * CPU-related functionality in the application.
 *
 * @author kq635
 */
public class CPUButtonHandler {

    private MainView mainView;

    /**
     * Constructor for the `CPUButtonHandler` class.
     *
     * @param mainView The main view of the application.
     */
    public CPUButtonHandler(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Handles the CPU-related action. It creates a CPU controller, associates
     * it with a CPU model and view, and hides the main view.
     */
    public void handle() {
        CPUController cpuController = new CPUController(new CPUModel(), new CPUView(mainView), mainView);
        mainView.setVisible(false);
    }
}
