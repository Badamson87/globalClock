package View_Controller;

import Helper.DBConnect;
import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class UpsertCustomerController implements Initializable {
    private static Stage upsertWindow;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField zip;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private ComboBox<Country> CountryCombo;
    @FXML
    private ComboBox<Division> DivisionCombo;
    private Connection conn;


    /**
     * Shows the modal of creation or edit of a customer
     * @param title designates between and update and create of new customer
     * @throws IOException
     */
    public void show(String title) throws IOException {
        Stage window = new Stage();
        upsertWindow = window;
        Parent root = FXMLLoader.load(UpsertCustomerController.class.getResource("upsertCustomer.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(new Scene(root, 700, 275));
        window.show();
    }

    /**
     * Close's the Customer update modal and calls to renew the list of customers.
     * @throws SQLException
     */
    public void close() throws SQLException {
        id.clear();
        name.clear();
        zip.clear();
        phone.clear();
        address.clear();
        CountryCombo.getItems().removeAll();
        DivisionCombo.getItems().removeAll();
        CustomersController.getAllCustomers();
        upsertWindow.close();
    }

    /**
     * This is a lambda function.
     * Sets the division on a previously selected Customer
     */
    public void setDivisions() {
   Country country = CountryCombo.getSelectionModel().getSelectedItem();
       if (country != null){
            DivisionCombo.getItems().clear();
          AtomicInteger counter = new AtomicInteger() ;
           Division.getAllDivisions().forEach((division -> {
               if(division.getCountry_ID() == country.getCountry_ID()){
                   DivisionCombo.getItems().add(counter.get(), division);
                   counter.getAndIncrement();
               }
           }
        ));
       }
    }

    /**
     * Calls to check that all customer fields have a value, then calls to update or create new customer.
     * @throws SQLException
     */
    public void save() throws SQLException {
        if (fieldsCheck() == true){
            if (id.getText().equals("")){
                saveNewCustomer();
            }
            else {
                saveEditCustomer();
            }
        }
        else {
            MessageModal.display("Unable To Save", "Please Complete Entire Form");
        }
    }

    /**
     * Gathers field values and saves a new customer
     * @throws SQLException
     */
    private void saveNewCustomer() throws SQLException {
        String n = name.getText();
        String a = address.getText();
        String z = zip.getText();
        String p = phone.getText();
        Date cd = new Timestamp(System.currentTimeMillis());
        String cb = HomeController.loggedInUser.getUser_Name();
        Date lu = new Timestamp(System.currentTimeMillis());
        String lub = HomeController.loggedInUser.getUser_Name();
         int d = DivisionCombo.getSelectionModel().getSelectedItem().getDivision_ID();
         String query = "Insert into customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "Values ('" + n + "', '" + a + "', '" + z + "', '" + p + "', '" + cd + "', '" + cb + "', '" + lu + "', '" + lub + "', " + d + ")";
         Statement st = conn.createStatement();
         int save = st.executeUpdate(query);
         if (save == 1){
             this.close();
         } else {
             MessageModal.display("Something went wrong", "Unable to save customer");
        }
    }

    /**
     * Gathers field values and updated and existing customer
     * @throws SQLException
     */
    private void saveEditCustomer() throws SQLException {
        String cusId = id.getText();
        String n = name.getText();
        String a = address.getText();
        String z = zip.getText();
        String p = phone.getText();
        Date lu = new Timestamp(System.currentTimeMillis());
        String lub = HomeController.loggedInUser.getUser_Name();
        int d = DivisionCombo.getSelectionModel().getSelectedItem().getDivision_ID();
        String query = "Update customers set Customer_Name = '" + n + "', Address = '" + a + "', Postal_Code = '" + z + "', Phone = '" + p + "',  Last_Update = '" + lu + "', Last_Updated_By = '" + lub + "', Division_ID = " + d +
                " WHERE Customer_ID = " + cusId;
        Statement st = conn.createStatement();
        int save = st.executeUpdate(query);
        if (save == 1){
            this.close();
        } else {
            MessageModal.display("Something went wrong", "Unable to save customer");
        }
    }

    /**
     * Checks that all fields have a value. Returned as a Boolean
     * @return
     */
    private boolean fieldsCheck(){
        if (name.getText().equals("")) { return false;}
        if (zip.getText().equals("")) {return false;}
        if (phone.getText().equals("")) {return false;}
        if (address.getText().equals("")) {return false;}
        if (CountryCombo.getSelectionModel().getSelectedItem() == null) {return false;}
        if (DivisionCombo.getSelectionModel().getSelectedItem() == null) {return false;}
        return true;
    }

    /**
     * Sets the existing property of a customer on case of update.
     * @throws SQLException
     */
    private void setCustomer() throws SQLException {
        Customer cus = CustomersController.selectedCustomer;
        if (cus != null && CustomersController.editMode) {
            id.setText(String.valueOf(cus.getId()));
            name.setText(cus.getCustomerName());
            address.setText(cus.getAddress());
            zip.setText(cus.getPostalCode());
            phone.setText(cus.getPhone());
            CountryCombo.setValue(Country.getCountryById(cus.getCountry_ID()));
            DivisionCombo.setValue(Division.getDivisionById(cus.getDivisionID()));
        }
        this.setDivisions();
    }

    /**
     * This is a lambda function.
     * Sets existing country on a customer on case of update.
     */
    private void setCountryOptions(){
        AtomicInteger counter = new AtomicInteger();
        ObservableList<Country> countries = Country.getAllCountries();
        countries.forEach( (country) -> {
            CountryCombo.getItems().add(counter.get(), country);
            counter.getAndIncrement();
        });
    }

    /**
     * Get the dbConnection and starts the process of setting and existing customer on case of update.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DBConnect.connection;
        this.setCountryOptions();
        try {
            this.setCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
