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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
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
    @FXML private DatePicker end;
    private Connection conn;
    private ObservableList<Customer> Customers = FXCollections.observableArrayList();
    private ObservableList<User> Users = FXCollections.observableArrayList();
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    /**
     * Shows the appointment modal, sets the title as update or create.
     * @param title
     * @throws IOException
     */
    public void show(String title) throws IOException {
        Stage window = new Stage();
        upsertWindow = window;
        Parent root = FXMLLoader.load(UpsertCustomerController.class.getResource("upsertAppointment.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(new Scene(root, 700, 400));
        window.show();
    }

    /**
     * Call to check field validity. Determines if save is an update or new Appointment
     * @throws SQLException
     * @throws ParseException
     */
    public void save() throws SQLException, ParseException {
        if (fieldsCheck() == true){
            if (appointmentsCheck().get() == true) {
                MessageModal.display("Unable To Save", "Conflicting scheduled appointment");
                return;
            }
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

    /**
     * Gather all appointment for selected customer and call to check for overlap
     * @return
     * @throws SQLException
     */
    private AtomicBoolean appointmentsCheck() throws SQLException {
        AtomicBoolean retVal = new AtomicBoolean(false);
        int newCustomerId = customerComboBox.getSelectionModel().getSelectedItem().getId();
        ObservableList<Appointment> appointments = Appointment.getAllAppointmentsByCustomerId(newCustomerId, conn);
        appointments.forEach((appointment -> {
            try {
                if (appointmentTimeCheck(appointment) == true){
                    retVal.set(true);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }));
        return retVal;
    }

    /**
     * Checks for appointment time overlap for selected customer
     * @param appointment
     * @return
     * @throws ParseException
     */
    private boolean appointmentTimeCheck(Appointment appointment) throws ParseException {
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        String start24 = date24Format.format(date12Format.parse(startTime.getSelectionModel().getSelectedItem()));
        // Format Start Time
        LocalDate startDate = start.getValue();;
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String startDateTimeString = startDate + " " + start24;

        LocalDateTime proposedNewDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime savedStartDate = LocalDateTime.parse(appointment.getStart().toString().replaceAll(" ", "T"));
        LocalDateTime savedEndDate = LocalDateTime.parse(appointment.getEnd().toString().replaceAll(" ", "T"));

        if (savedStartDate.isBefore(proposedNewDateTime) && savedEndDate.isAfter(proposedNewDateTime)) {
            return true;
        }
        return false;
    }

    /**
     * Save a new Appointment
     * @throws SQLException
     * @throws ParseException
     */
    private void saveNewAppointment() throws SQLException, ParseException {
        TimeController timeController = new TimeController();
        String newTitle = title.getText();
        String newDescription = description.getText();
        String newLocation = location.getText();
        String newType = type.getText();
        int newCustomerId = customerComboBox.getSelectionModel().getSelectedItem().getId();
        int newContactId = contactComboBox.getSelectionModel().getSelectedItem().getContact_ID();
        int newUserId = HomeController.loggedInUser.getUser_ID();
        String newCreatedBy = HomeController.loggedInUser.getUser_Name();
        String newLastUpdatedBY = HomeController.loggedInUser.getUser_Name();
        String newCreateDate = timeController.convertToUTC(LocalDateTime.now());
        String newUpdateDate = timeController.convertToUTC(LocalDateTime.now());

        // Date Time Format setup
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        String start24 = date24Format.format(date12Format.parse(startTime.getSelectionModel().getSelectedItem()));
        String end24 = date24Format.format(date12Format.parse(endTime.getSelectionModel().getSelectedItem()));

        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

        // Format Start Time
        LocalDate startDate = start.getValue();;
        String startDateTimeString = startDate + " " + start24;
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        String zonedStartDate = timeController.convertToUTC(startDateTime);

        // Format End time
        LocalDate endDate = end.getValue();;
        String endDateTimeString = endDate + " " + end24;
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        String zonedEndDate = timeController.convertToUTC(endDateTime);

        // Sql Start
        String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) "
                + "VALUES ('" + newTitle + "', '" + newDescription + "', '" + newLocation + "', '" + newType + "', '" + zonedStartDate + "', '" + zonedEndDate + "', '" + newCreateDate + "', '" + newCreatedBy + "', '" + newUpdateDate + "', '" + newLastUpdatedBY + "', '" + newCustomerId + "', '" + newUserId + "', '" + newContactId + "')";
        Statement st = conn.createStatement();
        int save = st.executeUpdate(query);
        if (save == 1){
            this.close();
        } else {
            MessageModal.display("Something went wrong", "Unable to save Appointment");
        }
    }

    /**
     * Update an existing appointment;
     * @throws ParseException
     * @throws SQLException
     */
    private void saveEditAppointment() throws ParseException, SQLException {
        String newId = appointmentId.getText();
        String newTitle = title.getText();
        String newDescription = description.getText();
        String newLocation = location.getText();
        String newType = type.getText();
        int newCustomerId = customerComboBox.getSelectionModel().getSelectedItem().getId();
        int newContactId = contactComboBox.getSelectionModel().getSelectedItem().getContact_ID();
        int newUserId = HomeController.loggedInUser.getUser_ID();
        String newLastUpdatedBY = HomeController.loggedInUser.getUser_Name();
        Date newUpdateDate = new Timestamp(System.currentTimeMillis());
        // Date Time Format setup
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        String start24 = date24Format.format(date12Format.parse(startTime.getSelectionModel().getSelectedItem()));
        String end24 = date24Format.format(date12Format.parse(endTime.getSelectionModel().getSelectedItem()));
        // Format Start Time
        LocalDate startDate = start.getValue();;
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String startDateTimeString = startDate + " " + start24;
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        // Format End time
        LocalDate endDate = start.getValue();;
        String endDateTimeString = endDate + " " + end24;
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        // Sql Start
        String query = "UPDATE appointments set Title = '" + newTitle + "', Description = '" + newDescription + "', Location = '" + newLocation + "', Type = '" + newType + "',  Start = '" + startDateTime + "', End = '" + endDateTime + "', Last_Update = '" + newUpdateDate + "',  Last_Updated_By = '" + newLastUpdatedBY + "', Customer_ID = " + newCustomerId + ", User_ID = " + newUserId + ", Contact_ID = " + newContactId +
        " WHERE Appointment_ID  = " + newId;
        Statement st = conn.createStatement();
        int save = st.executeUpdate(query);
        if (save == 1){
            this.close();
        } else {
            MessageModal.display("Something went wrong", "Unable to save Appointment");
        }


    }

    /**
     * Check that all Appointment fields have a value.
     * @return
     */
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

    /**
     * Set and existing appointment on case of update
     * @throws SQLException
     */
    public void setAppointment() throws SQLException {
        TimeController timeController = new TimeController();
       Appointment app = AppointmentsController.selectedAppointment;
       if (app != null && AppointmentsController.editMode) {
           appointmentId.setText(String.valueOf(app.getAppointment_ID()));
           title.setText(app.getTitle());
           location.setText(app.getLocation());
           type.setText(app.getType());
           description.setText(app.getDescription());
           customerComboBox.setValue(Customer.getCustomerById(app.getCustomer_ID()));
           contactComboBox.setValue(Contact.getContactById(app.getContact_ID()));
           startTime.setValue(timeController.splitDateTimeReturnTime(app.getStart()));
           endTime.setValue(timeController.splitDateTimeReturnTime(app.getEnd()));
           start.setValue(timeController.splitDateTimeReturnDate(app.getStart()));
           end.setValue(timeController.splitDateTimeReturnDate(app.getEnd()));
       }
    }

    /**
     * Gather and display list of contact options.
     * @throws SQLException
     */
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

    /**
     * Gather and display a list of customer options
     * @throws SQLException
     */
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

    /**
     * Get available options for start and end times
     */
    private void getTimeOptions(){
        startTime.getItems().setAll(TimeController.getTOptions());
        endTime.getItems().setAll(TimeController.getTOptions());
    }

    /**
     * Close the Appointment modal and calls to update the list of appointments.
     * @throws SQLException
     */
    public void close() throws SQLException {
        appointmentId.clear();
        title.clear();
        description.clear();
        location.clear();
        type.clear();
        customerComboBox.getItems().removeAll();
        contactComboBox.getItems().removeAll();
        startTime.getItems().removeAll();
        endTime.getItems().removeAll();
        AppointmentsController.getAllAppointments();
        upsertWindow.close();
    }

    /**
     * Sets Date pick options and eliminates the weekends as possibility
     * @return
     */
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

    /**
     * Sets the local dbConnection. Initializes the set of and existing Appointment in the case of update
     * @param url
     * @param resourceBundle
     */
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
