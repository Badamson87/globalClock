package View_Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpsertAppointmentController {
    private static Stage upsertWindow;
    public static void displayNew() throws IOException {
        Stage window = new Stage();
        upsertWindow = window;
        Parent root = FXMLLoader.load(UpsertCustomerController.class.getResource("upsertAppointment.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Customer");
        window.setScene(new Scene(root, 700, 400));
        window.showAndWait();
    }

    public void close(){
        upsertWindow.close();
    }
}
