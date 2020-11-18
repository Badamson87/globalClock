package View_Controller;

import Model.Appointment;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.xml.stream.Location;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpsertAppointmentController implements Initializable {
    private static Stage upsertWindow;
    @FXML private TextField appointmentId;
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField contact;
    @FXML private TextField customerId;
    @FXML private TextField userId;
    @FXML private TextField type;
    @FXML ComboBox<String> locationComboBox;
    @FXML private DatePicker start;
    @FXML private DatePicker end;

    public void show(String title) throws IOException {
        Stage window = new Stage();
        upsertWindow = window;
        Parent root = FXMLLoader.load(UpsertCustomerController.class.getResource("upsertAppointment.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(new Scene(root, 700, 400));
        window.show();
    }

    public void setAppointment(){
       Appointment app = AppointmentsController.selectedAppointment;
       if (app != null){
        title.setText(app.getTitle());
       }
    }

    public void close(){
        upsertWindow.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.locationComboBox.getItems().setAll("hello", "world");
        this.setAppointment();
    }
}
