package View_Controller;

import Helper.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {
@FXML
Button loginButton;

    public void attemptLogin() throws IOException {
        loginButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("home.fxml")));
       // todo was going to try to return something from mysql
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Helper.DBConnect.establishConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
