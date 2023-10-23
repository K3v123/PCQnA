/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Base.DatabaseManager;
import Control.CoolingController;
import Model.CoolingModel;
import view.CoolingView;
import view.MainView;

/*
 * The `CoolingButtonHandler` class is responsible for handling user interactions when the cooling-related button is clicked.
 * It initializes a `CoolingController` to manage the cooling component's details, display, and interactions.
 * The `DatabaseManager` is used to access the database for cooling-related data.
 *
 * @author kq635
 */
public class CoolingButtonHandler {

    private MainView mainView;

    /**
     * Constructor for the `CoolingButtonHandler` class.
     *
     * @param mainView The main view of the application.
     */
    public CoolingButtonHandler(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Handle the button click event by initializing the cooling controller and
     * hiding the main view.
     */
    public void handle() {
        DatabaseManager dbManager = new DatabaseManager();
        CoolingController coolingController = new CoolingController(new CoolingModel(), new CoolingView(), mainView, dbManager);
        mainView.setVisible(false);
    }
}
