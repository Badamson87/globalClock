package View_Controller;

import Helper.DBConnect;
import Model.Country;
import Model.Customer;
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
             String division = rs.getString("Division");
             DivisionCombo.getItems().add(counter.get(), division);
             counter.getAndIncrement();
            }
        }
    }

    private void setCustomer() throws SQLException {
        Customer cus = CustomersController.selectedCustomer;
        if (cus != null) {
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
