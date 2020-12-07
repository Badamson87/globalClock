package Model;

import Helper.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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


    public Customer(){
    }

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

    @Override
    public String toString(){
        return this.getName();
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return this.Customer_ID;
    }
    public String getCustomerName() {
        return this.Customer_Name;
    }
    public String getAddress() {
        return this.Address;
    }
    public String getPhone() {
        return this.Phone;
    }
    public String getPostalCode() {
        return this.Postal_Code;
    }
    public Date getCreateDate() {
        return this.Create_Date;
    }
    public String getCreatedBy() {
        return this.Created_By;
    }
    public Date getLastUpdate() {
        return this.Last_Update;
    }
    public String getLastUpdateBy() {
        return this.Last_Update_By;
    }
    public int getDivisionID() {
        return this.Division_ID;
    }
    public String getDivision() { return this.Division;}
    public int getCountry_ID() {return this.Country_ID;}
    public String getCountry() {return this.Country;}




}
