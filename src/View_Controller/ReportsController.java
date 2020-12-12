package View_Controller;

import Helper.DBConnect;
import Model.Appointment;
import Model.Contact;
import Model.Country;
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
    @FXML TableView<Appointment> appointmentsTable;

    @FXML TableView<Contact> contactsTable;
    @FXML Label contactTableLabel;

    private Connection conn;

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

    public void setCountryComboBox(){
        ObservableList<Country> countries =Country.getAllCountries();
        AtomicInteger counter = new AtomicInteger();
        countries.forEach((country) -> {
            countryComboBox.getItems().add(counter.get(), country);
            counter.getAndIncrement();
        });
    }

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

    public void submitAppointment(){
        this.appointmentsTable.setVisible(true);
        this.appointmentLabel.setVisible(true);
        // todo total number of customer appointments by type and month


    }

    public void submitContact(){
        this.contactTableLabel.setVisible(true);
        this.contactsTable.setVisible(true);
        // todo
        // schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID
    }

    public void submitCountry(){
        // todo
        // total number of appointments for a specific country broke down by division?
    }

    private void hideCountry(){
        countryComboBox.setVisible(false);
        countryLabel.setVisible(false);
    }

    private void hideContact(){
        this.contactComboBox.setVisible(false);
        this.contactLabel.setVisible(false);
    }

    private void hideButtons(){
        this.submitAppointment.setVisible(false);
        this.submitContact.setVisible(false);
        this.submitCountry.setVisible(false);
    }

    private void hideTables() {
        this.appointmentLabel.setVisible(false);
        this.appointmentsTable.setVisible(false);
        this.contactTableLabel.setVisible(false);
        this.contactsTable.setVisible(false);
    }

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
