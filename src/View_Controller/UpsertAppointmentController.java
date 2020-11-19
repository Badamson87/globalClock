package View_Controller;

import Helper.DBConnect;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class UpsertAppointmentController implements Initializable {
    private static Stage upsertWindow;
    @FXML private TextField appointmentId;
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField location;
    @FXML private TextField type;
    @FXML ComboBox<String> userComboBox;
    @FXML ComboBox<String> customerComboBox;
    @FXML ComboBox<String> contactComboBox;
    @FXML private DatePicker start;
    @FXML private DatePicker end;
    private Connection conn;
    private ObservableList<Customer> Customers = FXCollections.observableArrayList();
    private ObservableList<User> Users = FXCollections.observableArrayList();
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

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
           appointmentId.setText(String.valueOf(app.getAppointment_ID()));
           title.setText(app.getTitle());
           location.setText(app.getLocation());
           type.setText(app.getType());
           description.setText(app.getDescription());
           // todo set combo boxes and dates
           customerComboBox.setValue(app.getCustomerName());
           userComboBox.setValue(app.getUserName());
           contactComboBox.setValue(app.getContactName());
       }
    }

    private void getUserOptions() throws SQLException {
        String query = "select * from users";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int id = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            String pw = rs.getString("Password");
            Date createDate = rs.getDate("create_Date");
            String createBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            User newUser = new User(id, name, pw, createDate, createBy, lastUpdate, lastUpdateBy);
            this.Users.add(newUser);
        }
        for (int i = 0; i < this.Users.size(); i++){
            String name = this.Users.get(i).getUser_Name();
            userComboBox.getItems().add(i, name);
        }
    }

    private void getContactOptions() throws SQLException{
        String query = "select * from contacts";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact newCon = new Contact(id, name, email);
            this.contacts.add(newCon);
        }
        for (int i = 0; i < this.contacts.size(); i++){
            String name = this.contacts.get(i).getContact_Name();
            contactComboBox.getItems().add(i, name);
        }
    }

    private void getCustomerOptions() throws SQLException {
        String query = "select * from customers";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int id = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Date createDate = rs.getDate("create_Date");
            String createBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            Customer newCus = new Customer(id, customerName, address, phone, zip, createDate, createBy, lastUpdate, lastUpdateBy, divisionId);
            this.Customers.add(newCus);
        }
        for (int i = 0; i < this.Customers.size(); i++){
            String name = this.Customers.get(i).getCustomerName();
            customerComboBox.getItems().add(i, name);
        }
    }

    public void close(){
        upsertWindow.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DBConnect.connection;
        try {
            this.getCustomerOptions();
            this.getUserOptions();
            this.getCustomerOptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.setAppointment();
    }
}
