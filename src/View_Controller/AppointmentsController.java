package View_Controller;

import Helper.DBConnect;
import Helper.TimeController;
import Model.Appointment;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
    private static Connection conn;
    private static final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, String> startCol;
    @FXML
    private TableColumn<Appointment, String> endCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;
    @FXML
    private TableColumn<Appointment, String> customerCol;
    @FXML
    private ToggleGroup timeToggle;
    @FXML
    private RadioButton monthToggle;
    @FXML
    private RadioButton weekToggle;
    @FXML
    private RadioButton noneToggle;
    @FXML
    public static Appointment selectedAppointment;
    public static boolean editMode;

    /**
     * launches the upsert appointment modal in create mode.
     * @throws IOException
     */
    public void newAppointment() throws IOException, SQLException {
        editMode = false;
        UpsertAppointmentController upsertAppointmentController = new UpsertAppointmentController();
        upsertAppointmentController.show("Create Appointment");
        this.getAllAppointments();
    }

    /**
     * Launches the upsert appointment modal in Edit mode
     * @throws IOException
     */
    public void editAppointment() throws IOException, SQLException {
        if (appointmentTable.getSelectionModel().getSelectedItem() == null){
            MessageModal.display("No Appointment", "Please select an appointment to update");
            return;
        }
        editMode = true;
        selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        UpsertAppointmentController upsertAppointmentController = new UpsertAppointmentController();
        upsertAppointmentController.show("Update Appointment");
        this.getAllAppointments();
    }

    /**
     * sets up the appointment table with cell values and table data
     */
    private void initAppointmentTable() {
        TimeController timeController = new TimeController();
        appointmentIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAppointment_ID()).asObject());
        customerIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomer_ID()).asObject());
        titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        descriptionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        locationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        contactCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactName()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        startCol.setCellValueFactory(cellData -> new SimpleStringProperty(timeController.removeTFromTime(timeController.convertToLocal(cellData.getValue().getStart()))) );
        endCol.setCellValueFactory(cellData -> new SimpleStringProperty(timeController.removeTFromTime(timeController.convertToLocal(cellData.getValue().getEnd()))));
        customerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        appointmentTable.setItems(appointments);
    }

    /**
     * Calls to get all appointment dependent on the where clause
     * @throws SQLException
     */
    public void getAllAppointments() throws SQLException {
        appointments.clear();
        String whereClause = this.getWhereClause();
        String query = "select * from appointments inner join customers on appointments.Customer_ID = customers.Customer_ID inner join contacts on appointments.Contact_ID = contacts.Contact_ID inner join users on appointments.User_ID = users.User_ID" + whereClause + ";";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("Appointment_ID");
            int cusId = rs.getInt("Customer_ID");
            int contactId = rs.getInt("Contact_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String contact = rs.getString("Contact_Name");
            String type = rs.getString("Type");
            String start =  rs.getString("Start");
            String end = rs.getString("End");
            String customer = rs.getString("Customer_Name");
            String userName = rs.getString("User_Name");
            Appointment newApp = new Appointment(id, title, description, location, contact, contactId, type, start, end, customer, userName, cusId);
            appointments.add(newApp);
        }
    }

    /**
     *
     * @return string of start and date time appointment where clause
     */
    private String getWhereClause(){
        TimeController timeController = new TimeController();
        if (this.monthToggle.isSelected()) {
            String fromDate = timeController.convertToUTC(LocalDateTime.now());
            String toDate = timeController.convertToUTC(LocalDateTime.now().plusDays(30));
            return " where appointments.Start >= '" + fromDate + "' and appointments.Start <= '" +  toDate + "'";

        }
        if (this.weekToggle.isSelected()) {
            String fromDate = timeController.convertToUTC(LocalDateTime.now());
            String toDate = timeController.convertToUTC(LocalDateTime.now().plusDays(7));
            return " where appointments.Start >= '" + fromDate + "' and appointments.Start <= '" +  toDate + "'";
        }
        return "";
    }

    /**
     * calls to delete an appointment from the db
     * @throws SQLException
     */
    public void deleteAppointment() throws SQLException {
        if (appointmentTable.getSelectionModel().getSelectedItem() == null)
        {
            MessageModal.display("No Appointments", "Please select a appointment to delete");
            return;
        }
        int appointmentId = appointmentTable.getSelectionModel().getSelectedItem().getAppointment_ID();
        String type = appointmentTable.getSelectionModel().getSelectedItem().getType();
        String query = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentId;
        Statement st = conn.createStatement();
        int res = st.executeUpdate(query);
        if (res == 1){
            getAllAppointments();
            MessageModal.display("Success", "Appointment Delete ID: " + appointmentId + " Type: " + type);
        } else {
            MessageModal.display("Unable to delete appointment", "Something went wrong");
        }
    }

    /**
     * sets the initial toggle to no filter and adds the toggle choices
     */
    private void initTimeToggle(){
        timeToggle = new ToggleGroup();
        noneToggle.setSelected(true);
        timeToggle.getToggles().addAll(noneToggle, monthToggle, weekToggle);
    }

    /**
     * updates the search filter for where clause and calls to get all appointments
     * @throws SQLException
     */
    public void toggleTime() throws SQLException {
        getAllAppointments();
    }

    /**
     * Inits the time toggle and sets the local db connection
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initTimeToggle();
        this.conn = DBConnect.connection;
        this.initAppointmentTable();
        try {
            this.getAllAppointments();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
