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
    private Connection conn;
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
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
    private TableColumn<Customer, Integer> Country;

    public void newCustomer() throws IOException {
        UpsertCustomerController.displayNew();
    }

    private void getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customers";
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
            Customer newCus = new Customer(id, name, address, zip, phone, create_date, createdBy, lastUpdate, updatedBy, divisionId);
            customers.add(newCus);
        }
    }

    private void initCustomerTable(){
        Customer_ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        Customer_Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        Address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        Postal_Code.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
        Phone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        // todo first division and country
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
