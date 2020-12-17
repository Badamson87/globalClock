package View_Controller;

import Helper.DBConnect;
import Model.Appointment;
import Model.Customer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {
    private static Connection conn;
    private static final ObservableList<Customer> customers = FXCollections.observableArrayList();
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> Customer_ID;
    @FXML
    private TableColumn<Customer, String> Customer_Name;
    @FXML
    private TableColumn<Customer, String> Address;
    @FXML
    private TableColumn<Customer, String> Postal_Code;
    @FXML
    private TableColumn<Customer, String> Phone;
    @FXML
    private TableColumn<Customer, Integer> Division_ID;
    @FXML
    private TableColumn<Customer, String> DivisionCol;
    @FXML
    private TableColumn<Customer, Integer> Country_ID;
    @FXML
    private TableColumn<Customer, String> CountryCol;
    public static Customer selectedCustomer;
    public static boolean editMode;

    /**
     * calls upsert customer modal in create mode
     * @throws IOException
     * @throws SQLException
     */
    public void newCustomer() throws IOException, SQLException {
        editMode = false;
        UpsertCustomerController upsertCustomerController = new UpsertCustomerController();
        upsertCustomerController.show("Create Customer");
    }

    /**
     * populates the customer table with all customers.
     * @throws SQLException
     */
    public static void getAllCustomers() throws SQLException {
        customers.clear();
        String query = "select * from customers join first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID join countries on first_level_divisions.Country_ID = countries.Country_ID;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Date create_date = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String updatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Customer newCus = new Customer(id, name, address, phone, zip, create_date, createdBy, lastUpdate, updatedBy, divisionId, division, countryId, country);
            customers.add(newCus);
        }
    }

    /**
     * calls customer upsert modal in edit mode.
     * @throws IOException
     * @throws SQLException
     */
    public void editCustomer() throws IOException, SQLException {
        if (customerTable.getSelectionModel().getSelectedItem() == null){
            MessageModal.display("No Customer", "Please select a customer to update");
            return;
        }
        editMode = true;
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        UpsertCustomerController upsertCustomerController = new UpsertCustomerController();
        upsertCustomerController.show("Update Customer");
    }

    /**
     * Checks that a customer is selected and has no appointments before calling to deleting a customer from the DB.
     * @throws SQLException
     */
    public void checkDeleteCustomer() throws SQLException {
        if (customerTable.getSelectionModel().getSelectedItem() == null){
            MessageModal.display("No Customer", "Please select a customer to delete");
            return;
        }
        int customerId = customerTable.getSelectionModel().getSelectedItem().getId();
        ObservableList<Appointment> appointments = Appointment.getAllAppointmentsByCustomerId(customerId, conn);
        if (appointments.isEmpty()){
            this.deleteCustomer(customerId);
        }
        else{
            MessageModal.display("Unable to delete customer", "Can not delete a customer with appointments");
        }
    }

    /**
     * Deletes a customer from the DB and shows a confirmation modal
     * @param customerId
     * @throws SQLException
     */
    private void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM customers WHERE Customer_ID = " + customerId;
        Statement st = conn.createStatement();
        int res = st.executeUpdate(query);
        if (res == 1){
            getAllCustomers();
            MessageModal.display("Success", "Customer Deleted");
        } else {
            MessageModal.display("Unable to delete customer", "Something went wrong");
        }
    }

    /**
     * Sets up the customer table and data and cell data values
     */
    private void initCustomerTable(){
        Customer_ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        Customer_Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        Address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        Postal_Code.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
        Phone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        Division_ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDivisionID()).asObject());
        DivisionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDivision()));
        Country_ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCountry_ID()).asObject());
        CountryCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCountry()));
        customerTable.setItems(customers);
    }

    /**
     * sets the local db connection
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DBConnect.connection;
        this.initCustomerTable();
        try {
            this.getAllCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
