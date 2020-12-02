package View_Controller;

import Helper.DBConnect;
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
    private Connection conn;
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
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
    public static Appointment selectedAppointment;
    public static boolean editMode;


    public void newAppointment() throws IOException {
        editMode = false;
        UpsertAppointmentController upsertAppointmentController = new UpsertAppointmentController();
        upsertAppointmentController.show("Create Appointment");
    }

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

    private void initAppointmentTable() {
        appointmentIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAppointment_ID()).asObject());
        customerIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomer_ID()).asObject());
        titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        descriptionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        locationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        contactCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactName()));
        typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        startCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart()));
        endCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnd()));
        customerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        appointmentTable.setItems(appointments);
    }

    private void getAllAppointments() throws SQLException {
        String where;
        // todo need to know how the time limiter works, can it be one month forward or back? or one week forward of back?
        if (timeToggle.getSelectedToggle() == monthToggle) {
            System.out.print("month");
        } else {
            System.out.print("Week");
        }
        String query = "select * from appointments inner join customers on appointments.Customer_ID = customers.Customer_ID inner join contacts on appointments.Contact_ID = contacts.Contact_ID inner join users on appointments.User_ID = users.User_ID;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("Appointment_ID");
            int cusId = rs.getInt("Customer_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String contact = rs.getString("Contact_Name");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            String customer = rs.getString("Customer_Name");
            String userName = rs.getString("User_Name");
            Appointment newApp = new Appointment(id, title, description, location, contact, type, start, end, customer, userName, cusId);
            appointments.add(newApp);
        }
    }

    public void deleteAppointment(){
        // todo
    }

    private void initTimeToggle(){
        timeToggle = new ToggleGroup();
        monthToggle.setSelected(true);
        timeToggle.getToggles().addAll(monthToggle, weekToggle);
    }

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
