package View_Controller;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomersControllerTest {
    int id = -1;
    int divisionId = -3;
    String name = "testCustomer";
    String address = "testAddress";
    String phone = "testPhone";
    String zip = "postalCode";
    String createdBy = "testCreatedBy";
    String updatedBy = "testUpdatedBY";
    Date createDate = new Date();
    Date updatedDate = new Date();
    int divisionIdUpdated = -4;
    String nameUpdated = "testCustomerUpdated";
    String addressUpdated = "testAddressUpdated";
    String phoneUpdated = "testPhoneUpdated";
    String zipUpdated = "postalCodeUpdated";
    String updatedByUpdated = "testUpdatedBYUpdated";
    Date updatedDateUpdated = new Date();;
    ObservableList<Customer> customers = FXCollections.observableArrayList();


    @Test
    void newCustomer() {
        Customer customer = new Customer(id, name, address, phone, zip, createDate, createdBy, updatedDate, updatedBy, divisionId);
        assertEquals(id, customer.getId());
        assertEquals(name, customer.getCustomerName());
        assertEquals(address, customer.getAddress());
        assertEquals(phone, customer.getPhone());
        assertEquals(zip, customer.getPostalCode());
        assertEquals(createDate, customer.getCreateDate());
        assertEquals(createdBy, customer.getCreatedBy());
        assertEquals(updatedDate, customer.getLastUpdate());
        assertEquals(updatedBy, customer.getLastUpdateBy());
        assertEquals(divisionId, customer.getDivisionID());
    }

    @Test
    void editCustomer() {
        Customer updateCustomer = new Customer(id, name, address, phone, zip, createDate, createdBy, updatedDate, updatedBy, divisionId);
        updateCustomer.setCustomer_Name(nameUpdated);
        updateCustomer.setAddress(addressUpdated);
        updateCustomer.setPhone(phoneUpdated);
        updateCustomer.setPostal_Code(zipUpdated);
        updateCustomer.setLast_Update_By(updatedByUpdated);
        updateCustomer.setLast_Update(updatedDateUpdated);
        updateCustomer.setDivision_ID(divisionIdUpdated);
        assertEquals(nameUpdated, updateCustomer.getCustomerName());
        assertEquals(addressUpdated, updateCustomer.getAddress());
        assertEquals(phoneUpdated, updateCustomer.getPhone());
        assertEquals(zipUpdated, updateCustomer.getPostalCode());
        assertEquals(updatedDateUpdated, updateCustomer.getLastUpdate());
        assertEquals(updatedByUpdated, updateCustomer.getLastUpdateBy());
        assertEquals(divisionIdUpdated, updateCustomer.getDivisionID());

    }

    @Test
    void searchCustomers() {
       CustomersController customerController = new CustomersController();
//       customerController.initialize();
        

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
}