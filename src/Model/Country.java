package Model;

import Helper.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


/**
 * This class focuses on Countries. It is used throughout the project for the creation and display of countries
 */
public class Country extends Location {
    private int Country_ID;
    private String Country;
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * creates a country instance with no parameters
     */
    public Country(){
    }

    /**
     *
     * @return country id
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     *
     * @param country_ID set as country id
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     *
     * @return country name
     */
    public String getCountry() {
        return Country;
    }

    /**
     *
     * @param country set as country name
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * gets a country from the db with provided country id
     * @param Id country id
     * @return a country instance with provided id
     * @throws SQLException exception thrown during sql query
     */
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

    /**
     * creates a country instance with provides parameters
     * @param countryId set as country id
     * @param country set as country name
     * @param createDate set as country created date
     * @param createBy set as country created by
     * @param lastUpdate set as country last updated date
     * @param lastUpdateBy set as country last updated by
     */
    public Country(int countryId, String country, Date createDate, String createBy, Date lastUpdate, String lastUpdateBy) {
        this.Country_ID = countryId;
        this.Country = country;
        this.Create_Date = createDate;
        this.Created_By = createBy;
        this.Last_Update = lastUpdate;
        this.Last_Update_By = lastUpdateBy;
        this.name = country;
    }

    /**
     *
     * @return an observable list of all countries
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    /**
     *
     * @return country name as an override param for combo box
     */
    @Override
    public String toString(){
        return this.getName();
    }

    /**
     *
     * @return country name as name
     */
    public String getName(){
        return this.name;
    }

    /**
     * calls db to get all countries and sets to to the allCountries observable list
     * @throws SQLException exception thrown during sql query
     */
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
