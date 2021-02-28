package Model;

import Helper.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


/**
 * This class focuses on customers. It is used in the modeling and construction of customers
 */
public class Customer {
    public String name;
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Update_By;
    private int Division_ID;
    private String Division;
    private int Country_ID;
    private String Country;

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public void setLast_Update(Date last_Update) {
        Last_Update = last_Update;
    }

    public void setLast_Update_By(String last_Update_By) {
        Last_Update_By = last_Update_By;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public void setCountry(String country) {
        Country = country;
    }

    /**
     * empty division constructor
     */
    public Customer(){
    }

    /**
     *  creates a customer instance with params
     * @param Customer_ID provides the customer id
     * @param Customer_Name provides the customer name
     * @param Address provides the customer address
     * @param phone provides the customer phone
     * @param Postal_Code provides the customer postal code
     * @param Create_Date provides the customer created date
     * @param Created_By provides the customer created by
     * @param LastUpdate  provides the customer last updated date
     * @param Last_Update_By provides the customer last updated by
     * @param Division_ID provides the customer division id
     */
    public Customer(int Customer_ID, String Customer_Name, String Address, String phone, String Postal_Code, Date Create_Date,
                    String Created_By, Date LastUpdate, String Last_Update_By, int Division_ID){
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Phone = phone;
        this.Postal_Code = Postal_Code;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = LastUpdate;
        this.Last_Update_By = Last_Update_By;
        this.Division_ID = Division_ID;
        this.name = Customer_Name;
    }

    /**
     *  creates a instance of a custom with following params
     * @param Customer_ID provides the customer id
     * @param Customer_Name provides the customer name
     * @param Address provides the customer address
     * @param phone provides the customer phone
     * @param Postal_Code provides the customer postal code
     * @param Create_Date provides the customer created date
     * @param Created_By provides the customer created by
     * @param LastUpdate provides the customer last update date
     * @param Last_Update_By provides the customer last updated by
     * @param Division_ID  provides the customer division id
     * @param division provides the customer division name
     * @param Country_ID provides the customer country id
     * @param Country provides the customer country name
     */
    public Customer(int Customer_ID, String Customer_Name, String Address, String phone, String Postal_Code, Date Create_Date,
    String Created_By, Date LastUpdate, String Last_Update_By, int Division_ID, String division, int Country_ID, String Country){
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Phone = phone;
        this.Postal_Code = Postal_Code;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = LastUpdate;
        this.Last_Update_By = Last_Update_By;
        this.Division_ID = Division_ID;
        this.Division = division;
        this.Country_ID = Country_ID;
        this.Country = Country;
        this.name = Customer_Name;
    }

    /**
     *  db call to get a customer by customer id
     * @param Id customer id
     * @return returns a customer with customer id
     * @throws SQLException exception thrown during sql query
     */
    public static Customer getCustomerById(int Id) throws SQLException {
        Connection con = DBConnect.connection;
        String query = "SELECT * FROM customers where Customer_Id = '" + Id + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Customer newCustomer = new Customer();
        while (rs.next()){
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            java.sql.Date create_date = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            java.sql.Date lastUpdate = rs.getDate("Last_Update");
            String updatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            newCustomer = new Customer(id, name, address, phone, zip, create_date, createdBy, lastUpdate, updatedBy, divisionId);

        }
        return newCustomer;
    }

    /**
     *
     * @return override to return the string name of a customer for combo box
     */
    @Override
    public String toString(){
        return this.getName();
    }

    /**
     * works with override to return the name of a customer for combo box
     * @return customer name as a string
     */
    public String getName(){
        return this.name;
    }

    /**
     * return a customer id
     * @return customer id
     */
    public int getId() {
        return this.Customer_ID;
    }

    /**
     *
     * @return customer name
     */
    public String getCustomerName() {
        return this.Customer_Name;
    }

    /**
     *
     * @return customer address
     */
    public String getAddress() {
        return this.Address;
    }

    /**
     *
     * @return customer phone
     */
    public String getPhone() {
        return this.Phone;
    }

    /**
     *
     * @return customer postal code
     */
    public String getPostalCode() {
        return this.Postal_Code;
    }

    /**
     *
     * @return customer created date
     */
    public Date getCreateDate() {
        return this.Create_Date;
    }

    /**
     *
     * @return customer created by
     */
    public String getCreatedBy() {
        return this.Created_By;
    }

    /**
     *
     * @return customer last updated date
     */
    public Date getLastUpdate() {
        return this.Last_Update;
    }

    /**
     *
     * @return customer last updated by
     */
    public String getLastUpdateBy() {
        return this.Last_Update_By;
    }

    /**
     *
     * @return customer division id
     */
    public int getDivisionID() {
        return this.Division_ID;
    }

    /**
     *
     * @return customer division name
     */
    public String getDivision() { return this.Division;}

    /**
     *
     * @return customer country id
     */
    public int getCountry_ID() {return this.Country_ID;}

    /**
     *
     * @return customer country name
     */
    public String getCountry() {return this.Country;}




}
