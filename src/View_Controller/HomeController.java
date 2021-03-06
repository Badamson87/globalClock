package View_Controller;

import Helper.DBConnect;
import Helper.TimeController;
import Model.Appointment;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class acts as a base screen and changes/switches shown pannels based on customer options.
 */
public class HomeController implements Initializable {
    @FXML
    BorderPane borderPane;
    public static User loggedInUser;


    /**
     * shows the customer Pane
     * @throws IOException thrown exception
     */
    public void setCustomerPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("customers.fxml")));
    }

    /**
     * shows the app pane
     * @throws IOException thrown exception
     */
    public void setAppPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("appointments.fxml")));
    }

    /**
     * shows the about pane
     * @throws IOException thrown exception
     */
    public void setAboutPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("about.fxml")));
    }

    /**
     * shows the report pane
     * @throws IOException thrown exception
     */
    public void setReportPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("reports.fxml")));
    }



    /**
     * calls to set the about pane on login
     * @param url url
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.setAboutPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
