/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Control.MostAskedController;
import view.MainView;
import view.MostAskedView;

/**
 *
 * @author kq635
 */
public class MostAskedButtonHandler {

    private MainView mainView;

    // Constructor for MostAskedButtonHandler
    public MostAskedButtonHandler(MainView mainView) {
        this.mainView = mainView;
    }

    // Handle the button click action
    public void handle() {
        // Create an instance of MostAskedController and pass MostAskedView and mainView as parameters
        MostAskedController mostAskedController = new MostAskedController(new MostAskedView(), mainView);
        mainView.setVisible(false); // Hide the main view
    }
}
