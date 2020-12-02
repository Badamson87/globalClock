package View_Controller;

import Helper.DBConnect;
import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private ComboBox<String> CountryCombo;
    @FXML
    private ComboBox<String> DivisionCombo;
    private Connection conn;

    public void show(String title) throws IOException {
        Stage window = new Stage();
        upsertWindow = window;
        Parent root = FXMLLoader.load(UpsertCustomerController.class.getResource("upsertCustomer.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(new Scene(root, 700, 275));
        window.show();
    }

    public void close() {
        id.clear();
        name.clear();
        zip.clear();
        phone.clear();
        address.clear();
        CountryCombo.getItems().removeAll();
        DivisionCombo.getItems().removeAll();
        upsertWindow.close();
    }

    public void setDivisions() throws SQLException {
        String countryName = CountryCombo.getSelectionModel().getSelectedItem();
        if (countryName != null){
            DivisionCombo.getItems().clear();
            String query = "select * from countries join first_level_divisions on countries.Country_ID = first_level_divisions.COUNTRY_ID where Country = '" + countryName + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            AtomicInteger counter = new AtomicInteger();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                Date created = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String lastUpdateBy = rs.getString("Last_Updated_By");
                int countryID = rs.getInt("Country_ID");
             Division division = new Division(divisionId, name, created, createdBy, lastUpdate, lastUpdateBy, countryID);
             DivisionCombo.getItems().add(counter.get(), division.getDivision());
             counter.getAndIncrement();
            }
        }
    }

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

    private void saveNewCustomer() throws SQLException {
        // todo
        String n = name.getText();
        String a = address.getText();
        String z = zip.getText();
        String p = phone.getText();
        Date cd = new Date();
        //String cb =
        Date lu = new Date();
        //String lub =
        //int d =
        // String query = "Insert into customers ('Customer_Name', 'Address', 'Postal_Code', 'Phone', 'Create_Date', 'Create_By', 'Last_Update', 'Last_Update_By', 'Division_ID') " +
        //        "Values (" + n + a + z + p + cd + cb + lu + lub + d + ")";
        // Statement st = conn.createStatement();
        // ResultSet rs = st.executeQuery(query);

    }

    private void saveEditCustomer() {
        // todo
    }

    private boolean fieldsCheck(){
        if (name.getText().equals("")) { return false;}
        if (zip.getText().equals("")) {return false;}
        if (phone.getText().equals("")) {return false;}
        if (address.getText().equals("")) {return false;}
        if (CountryCombo.getSelectionModel().getSelectedItem() == null) {return false;}
        if (DivisionCombo.getSelectionModel().getSelectedItem() == null) {return false;}
        return true;
    }

    private void setCustomer() throws SQLException {
        Customer cus = CustomersController.selectedCustomer;
        if (cus != null && CustomersController.editMode) {
            id.setText(String.valueOf(cus.getId()));
            name.setText(cus.getCustomerName());
            address.setText(cus.getAddress());
            zip.setText(cus.getPostalCode());
            phone.setText(cus.getPhone());
            CountryCombo.setValue(cus.getCountry());
           DivisionCombo.setValue(cus.getDivision());
        }
        this.setDivisions();
    }

    private void setCountryOptions(){
        AtomicInteger counter = new AtomicInteger();
        ObservableList<Country> countries = Country.getAllCountries();
        countries.forEach( (country) -> {
            CountryCombo.getItems().add(counter.get(), country.getCountry());
            counter.getAndIncrement();
        });
    }

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
