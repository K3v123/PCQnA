/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonHandler;
import Base.DatabaseManager;
import Control.TPUController;
import Model.TPUModel;
import view.MainView;
import view.TPUView;

/**
 *
 * This class represents a handler for TPU-related interactions in the
 * application. It is responsible for managing actions related to TPU
 * components.
 *
 * @author kq635
 */
public class TPUButtonHandler {

    private MainView mainView;
    private DatabaseManager dbManager;

    /**
     * Constructor for the TPUButtonHandler class.
     *
     * @param mainView The main view of the application.
     * @param dbManager The database manager for accessing TPU data.
     */
    public TPUButtonHandler(MainView mainView, DatabaseManager dbManager) {
        this.mainView = mainView;
        this.dbManager = dbManager;
    }

    /**
     * Handle the TPU-related actions. This method initializes a TPUController
     * and switches the visibility of the main view.
     */
    public void handle() {
        TPUController tpuController = new TPUController(new TPUModel(), new TPUView(), mainView, dbManager);
        mainView.setVisible(false);
    }
}
