package View_Controller;

import Helper.DBConnect;
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

    public void newCustomer() throws IOException, SQLException {
        editMode = false;
        UpsertCustomerController upsertCustomerController = new UpsertCustomerController();
        upsertCustomerController.show("Create Customer");
    }

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

    public void deleteCustomer(){
        // todo
    }

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
