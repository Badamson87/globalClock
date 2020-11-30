package Model;

import java.util.Date;

public class Customer {
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
