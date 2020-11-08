package View_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {
@FXML
Button loginButton;

    public void attemptLogin() throws IOException {
        loginButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("home.fxml")));
    }
}
