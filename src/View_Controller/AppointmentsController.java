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
    public static String searchFilter;

    /**
     * launches the upsert appointment modal in create mode.
     * @throws IOException
     */
    public void newAppointment() throws IOException {
        editMode = false;
        UpsertAppointmentController upsertAppointmentController = new UpsertAppointmentController();
        upsertAppointmentController.show("Create Appointment");
    }

    /**
     * Launches the upsert appointment modal in Edit mode
     * @throws IOException
     */
    public void editAppointment() throws IOException {
        if (appointmentTable.getSelectionModel().getSelectedItem() == null){
            MessageModal.display("No Appointment", "Please select an appointment to update");
            return;
        }
        editMode = true;
        selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        UpsertAppointmentController upsertAppointmentController = new UpsertAppointmentController();
        upsertAppointmentController.show("Update Appointment");
    }

    /**
     * sets up the appointment table with cell values and table data
     */
    private void initAppointmentTable() {
        appointmentIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAppointment_ID()).asObject());
        customerIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomer_ID()).asObject());
        titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        descriptionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        locationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        contactCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactName()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        startCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().toString()));
        endCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnd().toString()));
        customerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        appointmentTable.setItems(appointments);
    }

    /**
     * Calls to get all appointment dependent on the where clause
     * @throws SQLException
     */
    public static void getAllAppointments() throws SQLException {
        appointments.clear();
        String whereClause = "";
        System.out.println(searchFilter);
        if (searchFilter != null && searchFilter.equals("Month")) {
            // todo update where clause for 30 days
            System.out.println("month");
        }
        if (searchFilter != null && searchFilter.equals("Week")) {
            // todo update where clause for 7 days
           System.out.println("week");
        }
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
            Date start =  rs.getDate("Start");
            Date end = rs.getDate("End");
            String customer = rs.getString("Customer_Name");
            String userName = rs.getString("User_Name");
            Appointment newApp = new Appointment(id, title, description, location, contact, contactId, type, start, end, customer, userName, cusId);
            appointments.add(newApp);
        }
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
        this.searchFilter = this.timeToggle.getSelectedToggle().toString();
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
