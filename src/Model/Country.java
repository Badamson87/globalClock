package Model;

import Helper.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Country {
    public String name;
    private int Country_ID;
    private String Country;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Update_By;
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public Country(){
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Date getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public Date getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Date last_Update) {
        Last_Update = last_Update;
    }

    public String getLast_Update_By() {
        return Last_Update_By;
    }

    public void setLast_Update_By(String last_Update_By) {
        Last_Update_By = last_Update_By;
    }

    public static Country getCountryById(int Id) throws SQLException {
        Connection con = DBConnect.connection;
        String query = "SELECT * FROM countries where Country_Id = '" + Id + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Country newCountry = new Country();
        while (rs.next()){
            int id = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Date create = rs.getDate("Create_Date");
            String createBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String updateBy = rs.getString("Last_Updated_By");
            newCountry = new Country(id, country, create, createBy, lastUpdate, updateBy);
        }
            return newCountry;
    }

    public Country(int countryId, String country, Date createDate, String createBy, Date lastUpdate, String lastUpdateBy) {
        this.Country_ID = countryId;
        this.Country = country;
        this.Create_Date = createDate;
        this.Created_By = createBy;
        this.Last_Update = lastUpdate;
        this.Last_Update_By = lastUpdateBy;
        this.name = country;
    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    public String getName(){
        return this.name;
    }

    public static void setAllCountries() throws SQLException {
        Connection con = DBConnect.connection;
        String query = "SELECT * FROM countries";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int id = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Date create = rs.getDate("Create_Date");
            String createBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String updateBy = rs.getString("Last_Updated_By");
            Country newCountry = new Country(id, country, create, createBy, lastUpdate, updateBy);
            allCountries.add(newCountry);
        }
    }

}
