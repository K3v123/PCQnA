/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Control.MemoryController;
import Model.MemoryModel;
import view.MainView;
import view.MemoryView;

/**
 * ss handles memory button clicks in the application.
 *
 * @author kq635
 */
public class MemoryButtonHandler {

    private MainView mainView;

    // Constructor to initialize the handler with the main view
    public MemoryButtonHandler(MainView mainView) {
        this.mainView = mainView;
    }

    /*
     * This method is called when the memory button is clicked.
     * It creates a MemoryController to manage memory-related actions and views
     * and hides the main view.
     */
    public void handle() {
        // Create a MemoryController to manage memory-related actions and views
        MemoryController memoryController = new MemoryController(new MemoryModel(), new MemoryView(), mainView);

        // Hide the main view to display memory-related information
        mainView.setVisible(false);
    }
}
