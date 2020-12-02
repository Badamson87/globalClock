package View_Controller;

import Helper.DBConnect;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {
    @FXML
    Button loginButton;
    @FXML TextField UserName;
    @FXML TextField Password;
    private Connection conn;

    public void attemptLogin() throws IOException, SQLException {
        if (UserName.getText().equals("") || Password.getText().equals("")){
            MessageModal.display("Unable to login", "Please enter user name and password");
        } else {
            String un = UserName.getText();
            String pw = Password.getText();
            String query = "Select * from users where User_Name = '" + un + "' and password = '" + pw + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
            {
                int id = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Date createDate = rs.getDate("Create_Date");
                String createBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String updateBy = rs.getString("Last_Updated_By");
                User newUser = new User(id, userName, password, createDate, createBy, lastUpdate, updateBy);
                this.setUser(newUser);
            } else
                {
                    MessageModal.display("Unable to Login", "Authorization failed");
                    // todo record failed login attempt?
                }
        }
    }

    private void setUser(User user) throws IOException {
            HomeController.loggedInUser = user;
            this.navigateHome();
            // todo record logged in user file
    }


    private void navigateHome() throws IOException {
        loginButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("home.fxml")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
           this.conn = Helper.DBConnect.establishConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
