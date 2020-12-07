package View_Controller;

import Helper.DBConnect;
import Helper.TimeController;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class UpsertAppointmentController implements Initializable {
    private static Stage upsertWindow;
    @FXML private TextField appointmentId;
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField location;
    @FXML private TextField type;
    @FXML ComboBox<Customer> customerComboBox;
    @FXML ComboBox<Contact> contactComboBox;
    @FXML ComboBox<String> startTime;
    @FXML ComboBox<String> endTime;
    @FXML private DatePicker start;
    @FXML private DatePicker end; // todo specify no weekends
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

    public void save(){
        if (fieldsCheck() == true){
            if (appointmentId.getText().equals("")){
                saveNewAppointment();
            }
            else {
                saveEditAppointment();
            }
        }
        else {
            MessageModal.display("Unable To Save", "Please Complete Entire Form");
        }
    }

    private void saveNewAppointment(){
        System.out.println("Save");
    }

    private void saveEditAppointment(){
        System.out.println("Edit");
    }

    private boolean fieldsCheck(){
        if (title.getText().equals("")) {return false;}
        if (description.getText().equals("")) {return false;}
        if (location.getText().equals("")) {return false;}
        if (type.getText().equals("")) {return false;}
        if (customerComboBox.getSelectionModel().getSelectedItem() == null) {return false;}
        if (contactComboBox.getSelectionModel().getSelectedItem() == null) {return false;}
        if (startTime.getSelectionModel().getSelectedItem() == null) {return false;}
        if (endTime.getSelectionModel().getSelectedItem() == null) {return false;}
        if (start.getValue() == null) {return false;}
        if (end.getValue() == null) {return false;}
        return true;
    }

    public void setAppointment() throws SQLException {
       Appointment app = AppointmentsController.selectedAppointment;
       if (app != null && AppointmentsController.editMode) {
           appointmentId.setText(String.valueOf(app.getAppointment_ID()));
           title.setText(app.getTitle());
           location.setText(app.getLocation());
           type.setText(app.getType());
           description.setText(app.getDescription());
           customerComboBox.setValue(Customer.getCustomerById(app.getCustomer_ID()));
           contactComboBox.setValue(Contact.getContactById(app.getContact_ID()));
           startTime.setValue(TimeController.splitDateTimeReturnTime(app.getStart()));
           endTime.setValue(TimeController.splitDateTimeReturnTime(app.getEnd()));
           start.setValue(TimeController.splitDateTimeReturnDate(app.getStart()));
           end.setValue(TimeController.splitDateTimeReturnDate(app.getEnd()));
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
        AtomicInteger counter = new AtomicInteger();
        this.contacts.forEach((contact) -> {
            contactComboBox.getItems().add(counter.get(), contact);
            counter.getAndIncrement();
        });
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
        AtomicInteger counter = new AtomicInteger();
        this.Customers.forEach((customer) -> {
            customerComboBox.getItems().add(counter.get(), customer);
            counter.getAndIncrement();
        });
    }

    private void getTimeOptions(){
        startTime.getItems().setAll(TimeController.getTOptions());
        endTime.getItems().setAll(TimeController.getTOptions());
    }

    public void close(){
        upsertWindow.close();
    }

    private Callback<DatePicker, DateCell> setDatePickerOptions() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (date.getDayOfWeek() == DayOfWeek.SATURDAY //
                                || date.getDayOfWeek() == DayOfWeek.SUNDAY
                                || date.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #FFFFFF;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DBConnect.connection;
        this.getTimeOptions();
        Callback<DatePicker, DateCell> dayCellFactory = this.setDatePickerOptions();
        start.setDayCellFactory(dayCellFactory);
        end.setDayCellFactory(dayCellFactory);

        try {
            this.getCustomerOptions();
            this.getContactOptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            this.setAppointment();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
