package View_Controller;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {
    @FXML
    Button loginButton;
    @FXML TextField UserName;
    @FXML TextField Password;
    private Connection conn;

    /**
     * Checks that fields are not null, Attempts to login
     * @throws IOException
     * @throws SQLException
     */
    public void attemptLogin() throws IOException, SQLException {
        if (UserName.getText().equals("") || Password.getText().equals("")){
            MessageModal.display("Unable to login", "Please enter user name and password");
        } else {
            try {
                String un = UserName.getText();
                String pw = Password.getText();
                String query = "Select * from users where User_Name = '" + un + "' and password = '" + pw + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int id = rs.getInt("User_ID");
                    String userName = rs.getString("User_Name");
                    String password = rs.getString("Password");
                    Date createDate = rs.getDate("Create_Date");
                    String createBy = rs.getString("Created_By");
                    Date lastUpdate = rs.getDate("Last_Update");
                    String updateBy = rs.getString("Last_Updated_By");
                    User newUser = new User(id, userName, password, createDate, createBy, lastUpdate, updateBy);
                    this.setUser(newUser);
                } else {
                    MessageModal.display("Unable to Login", "Authorization failed");
                    this.recordLoginAttempt(un, false);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * sets the local logged in user
     * @param user
     * @throws IOException
     */
    private void setUser(User user) throws IOException {
            HomeController.loggedInUser = user;
            this.navigateHome();
            try{
                this.recordLoginAttempt(user.getUser_Name(), true);
            } catch (IOException e) {
                System.out.println(e);
            }
    }

    /**
     * record user log in attempts and locations
     * @param user user name of attempted login
     * @param successful if login was successful
     */
    private void recordLoginAttempt(String user, boolean successful) throws IOException {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime ldc = LocalDateTime.now();
        FileWriter fw = new FileWriter("login_activity.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println ("Name: " + user + " Location: " + zoneId + " DateTime: " + ldc + " Successful Log in: " + successful);
        pw.close();
    }

    /**
     * Navigates to the home screen on successful login
     * @throws IOException
     */
    private void navigateHome() throws IOException {
        loginButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("home.fxml")));
    }

    /**
     * sets the local db Connection
     * @param url
     * @param resourceBundle
     */
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
