package View_Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML
    ComboBox typeBox;
    @FXML
    ComboBox byBox;
    @FXML
    ComboBox locationBox;
    @FXML Label locationLabel;

    public void selectType(){
        Object choice = typeBox.getValue();
        if (choice == "Total Appointments"){
            setByAppointment();
        } else {
            setByContact();
        }
    }

    public void setByAppointment(){
        byBox.getItems().clear();
        byBox.getItems().addAll("Appointment Type", "Month", "Location");
    }

    public void setByContact(){
        byBox.setPromptText("Select Customer");
        byBox.getItems().clear();
        byBox.getItems().add("all customers");
    }

    public void selectBy(){
        if (byBox.getValue() == "Location"){
            locationBox.setVisible(true);
            locationLabel.setVisible(true);
        } else {
            hideLocation();
        }
    }

    public void submit(){

    }

    private void hideLocation(){
        locationBox.setVisible(false);
        locationLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideLocation();
        typeBox.getItems().addAll("Total Appointments", "Contact Schedule");
    }
}
