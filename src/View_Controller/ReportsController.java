package View_Controller;

import Helper.DBConnect;
import Model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ReportsController implements Initializable {
    @FXML
    ComboBox typeBox;
    @FXML
    ComboBox<Contact> contactComboBox;
    @FXML
    ComboBox<Country> countryComboBox;
    @FXML SplitPane splitPane;
    @FXML AnchorPane rightPane;
    @FXML Button submitAppointment;
    @FXML Button submitContact;
    @FXML Button submitCountry;
    @FXML Label countryLabel;
    @FXML Label contactLabel;

    @FXML Label appointmentLabel;
    @FXML TableView<AppointmentType> appointmentsTable;
    @FXML private TableColumn<AppointmentType, String> AppointmentType;
    @FXML private TableColumn<AppointmentType, Integer> Jan;
    @FXML private TableColumn<AppointmentType, Integer> Feb;
    @FXML private TableColumn<AppointmentType, Integer> Mar;
    @FXML private TableColumn<AppointmentType, Integer> Apr;
    @FXML private TableColumn<AppointmentType, Integer> May;
    @FXML private TableColumn<AppointmentType, Integer> Jun;
    @FXML private TableColumn<AppointmentType, Integer> Jul;
    @FXML private TableColumn<AppointmentType, Integer> Aug;
    @FXML private TableColumn<AppointmentType, Integer> Sep;
    @FXML private TableColumn<AppointmentType, Integer> Oct;
    @FXML private TableColumn<AppointmentType, Integer> Nov;
    @FXML private TableColumn<AppointmentType, Integer> Dec;

    @FXML TableView<Appointment> contactsTable;
    @FXML Label contactTableLabel;
    @FXML private TableColumn<Appointment, Integer> ContactCol;
    @FXML private TableColumn<Appointment, Integer> AppIDCol;
    @FXML private TableColumn<Appointment, String> TitleCol;
    @FXML private TableColumn<Appointment, String> TypeCol;
    @FXML private TableColumn<Appointment, String> DescriptionCol;
    @FXML private TableColumn<Appointment, String> StartCol;
    @FXML private TableColumn<Appointment, String> EndCol;
    @FXML private TableColumn<Appointment, Integer> CusIDCol;

    @FXML TableView<CountryReport> countryTable;
    @FXML Label countryTableLabel;
    @FXML private TableColumn<CountryReport, String> CountryCol;
    @FXML private TableColumn<CountryReport, String> DivisionCol;
    @FXML private TableColumn<CountryReport, Integer> CustomerCol;
    @FXML private TableColumn<CountryReport, Integer> AppointmentCol;

    private Connection conn;

    /**
     * Sets the selected report type and shows the required table
     */
    public void selectType() {
        Object choice = typeBox.getValue();
        this.hideButtons();
        if (choice == "Total Appointments"){
            hideContact();
            hideCountry();
            this.submitAppointment.setVisible(true);
        } else if (choice == "Contact Schedule"){
            hideCountry();
            this.contactLabel.setVisible(true);
            this.contactComboBox.setVisible(true);
            this.submitContact.setVisible(true);
        } else {
            this.countryComboBox.setVisible(true);
            this.countryLabel.setVisible(true);
            this.submitCountry.setVisible(true);
            hideContact();
        }
    }

    /**
     * set choices for country report
     */
    public void setCountryComboBox(){
        ObservableList<Country> countries =Country.getAllCountries();
        AtomicInteger counter = new AtomicInteger();
        countries.forEach((country) -> {
            countryComboBox.getItems().add(counter.get(), country);
            counter.getAndIncrement();
        });
    }

    /**
     * set choices for contact options
     * @throws SQLException
     */
    public void setContactOptions() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String query = "select * from contacts";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact newCon = new Contact(id, name, email);
            contacts.add(newCon);
        }
        AtomicInteger counter = new AtomicInteger();
        contacts.forEach((contact) -> {
            contactComboBox.getItems().add(counter.get(), contact);
            counter.getAndIncrement();
        });
    }

    /**
     * shows appointment table and displays all appointments with their type
     * @throws SQLException
     */
    public void submitAppointment() throws SQLException {
        this.hideTables();
        this.appointmentsTable.setVisible(true);
        this.appointmentLabel.setVisible(true);
        ObservableList<Appointment> appointments = Appointment.getAllAppointments(conn);
        ObservableList<AppointmentType> appointmentTypes = FXCollections.observableArrayList();
        appointments.forEach((appointment -> {
            AtomicBoolean found = new AtomicBoolean(false);
           appointmentTypes.forEach((type -> {
               if (appointment.getType().equals(type.getType())){
                   found.set(true);
                   this.incrementAppointment(type, appointment);
               }
           }));
            if (found.get() == false){
                AppointmentType appointmentType = new AppointmentType(appointment.getType());
                this.incrementAppointment(appointmentType, appointment );
                appointmentTypes.add(appointmentType);
            }
        }));
        AppointmentType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        Jan.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJan()).asObject());
        Feb.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFeb()).asObject());
        Mar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMar()).asObject());
        Apr.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getApr()).asObject());
        May.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMay()).asObject());
        Jun.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJun()).asObject());
        Jul.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJul()).asObject());
        Aug.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAug()).asObject());
        Sep.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSep()).asObject());
        Oct.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOct()).asObject());
        Nov.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNov()).asObject());
        Dec.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDec()).asObject());
        appointmentsTable.setItems(appointmentTypes);
    }

    /**
     * increments each appointment type by month
     * @param appointmentType
     * @param appointment
     */
    public void incrementAppointment(AppointmentType appointmentType, Appointment appointment){
        String start = appointment.getStart();
        String month = start.substring(5, 7);
        switch(month) {
            case "01":
                appointmentType.incrementJan();
                break;
            case "02":
                appointmentType.incrementFeb();
                break;
            case "03":
                appointmentType.incrementMar();
                break;
            case "04":
                appointmentType.incrementApr();
                break;
            case "05":
                appointmentType.incrementMay();
                break;
            case "06":
                appointmentType.incrementJun();
                break;
            case "07":
                appointmentType.incrementJul();
                break;
            case "08":
                appointmentType.incrementAug();
                break;
            case "09":
                appointmentType.incrementSep();
                break;
            case "10":
                appointmentType.incrementOct();
                break;
            case "11":
                appointmentType.incrementNov();
                break;
            case "12":
                appointmentType.incrementDec();
                break;
            default:
                return;
        }
    }

    /**
     * shows contact table and gathers all appointments
     * @throws SQLException
     */
    public void submitContact() throws SQLException {
        Contact selectedContact = this.contactComboBox.getSelectionModel().getSelectedItem();
        if (selectedContact == null){
            MessageModal.display("Unable to report", "Please select a contact");
            return;
        }
        this.hideTables();
        this.contactTableLabel.setVisible(true);
        this.contactsTable.setVisible(true);
        ObservableList<Appointment> appointments = Appointment.getAllAppointmentsByContactID(selectedContact.getContact_ID(), conn);
        ContactCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getContact_ID()).asObject());
        AppIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAppointment_ID()).asObject());
        CusIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomer_ID()).asObject());
        TypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        TitleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        DescriptionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        StartCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart()));
        EndCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnd()));
        this.contactsTable.setItems(appointments);
    }

    /**
     * shows country table and queries for all appointments by country and customer
     * @throws SQLException
     */
    public void submitCountry() throws SQLException {
        Country selectedCountry = this.countryComboBox.getSelectionModel().getSelectedItem();
        if (selectedCountry == null){
            MessageModal.display("Unable to report", "Please Select A Country");
            return;
        }
        this.hideTables();
        this.countryTable.setVisible(true);
        this.countryTableLabel.setVisible(true);
        int countryID = selectedCountry.getCountry_ID();
        ObservableList<CountryReport> countryReports = FXCollections.observableArrayList();
        String query = "select countries.Country, fld.Division, COUNT(appointments.Appointment_ID) as numberOfAppointments, count(distinct customers.Customer_ID) as numberOfCustomers\n" +
                "            from countries\n" +
                "            join first_level_divisions as fld on fld.COUNTRY_ID = countries.Country_ID\n" +
                "            join customers on customers.Division_ID = fld.Division_ID\n" +
                "            join appointments on appointments.Customer_ID = customers.Customer_ID\n" +
                "            where countries.Country_ID = " + countryID + " group by fld.Division_ID;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            String country = rs.getString("Country");
            String division = rs.getString("Division");
            int app = rs.getInt("numberOfAppointments");
            int cus = rs.getInt("numberOfCustomers");
           CountryReport countryReport = new CountryReport(country, division, app, cus);
           countryReports.add(countryReport);
        }
        CountryCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCountry()));
        DivisionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDivision()));
        CustomerCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumberOfCustomers()).asObject());
        AppointmentCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumberOfAppointments()).asObject());
        this.countryTable.setItems(countryReports);
    }

    /**
     * Hides the country comboBox and label
     */
    private void hideCountry(){
        countryComboBox.setVisible(false);
        countryLabel.setVisible(false);
    }

    /**
     * Hides the contact comboBox and label
     */
    private void hideContact(){
        this.contactComboBox.setVisible(false);
        this.contactLabel.setVisible(false);
    }

    /**
     * Hides report submit buttons
     */
    private void hideButtons(){
        this.submitAppointment.setVisible(false);
        this.submitContact.setVisible(false);
        this.submitCountry.setVisible(false);
    }

    /**
     * Hides all tables and labels
     */
    private void hideTables() {
        this.appointmentLabel.setVisible(false);
        this.appointmentsTable.setVisible(false);
        this.contactTableLabel.setVisible(false);
        this.contactsTable.setVisible(false);
        this.countryTableLabel.setVisible(false);
        this.countryTable.setVisible(false);
    }

    /**
     * Calls to hide combo box's and tables.
     * Set initial report type options
     * sets local db Connection
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DBConnect.connection;
        this.hideTables();
        this.hideCountry();
        this.hideContact();
        this.hideButtons();
        this.setCountryComboBox();
        try {
            this.setContactOptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        hideCountry();
        typeBox.getItems().addAll("Total Appointments", "Contact Schedule", "Country");
    }
}
