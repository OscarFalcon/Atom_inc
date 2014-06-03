package PMAWizard;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.scene.Node;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class VistaNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN    = "PMAWizard.fxml";
    public static final String ADDVEHICLE = "AddVehicleScreen.fxml";
    public static final String CREATECUSTOMER = "CreateCustScreen.fxml";
    public static final String SELECTCUSTOMER = "SelectCustomerScreen.fxml";
    public static final String SELECTVEHICLE = "SelectVehicleScreen.fxml";
    public static final String FINALCOMMENTS = "FinalCommentsScreen.fxml";

    /** The main application layout controller. */
    private static MainController mainController;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public static void setMainController(MainController mainController) {
        VistaNavigator.mainController = mainController;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml) {
        try {
            mainController.setVista((Node) FXMLLoader.load(
                    VistaNavigator.class.getResource(fxml)
                )
            );            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}