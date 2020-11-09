package View_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    BorderPane borderPane;

    public void setCustomerPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("customers.fxml")));
    }

    public void setAppPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("appointments.fxml")));
    }

    public void setAboutPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("about.fxml")));
    }
    public void setReportPane() throws IOException {
        borderPane.setCenter(FXMLLoader.load(getClass().getResource("reports.fxml")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.setAboutPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
