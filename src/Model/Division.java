package Model;

import Helper.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Division {
    public String name;
    private int Division_ID;
    private String Division;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Updated_By;
    private int Country_ID;
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
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

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public static ObservableList<Division> getAllDivisions(){
        return  allDivisions;
    }

    public static Division getDivisionById(int Id) throws SQLException {
        Connection con = DBConnect.connection;
        String query = "SELECT * FROM first_level_divisions where Division_Id = '" + Id + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Division newDivision = new Division();
        while (rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            Date created = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");
           newDivision = new Division(divisionId, name, created, createdBy, lastUpdate, lastUpdateBy, countryID);
        }
        return newDivision;
    }

    public static void setAllDivisions() throws SQLException {
        Connection con = DBConnect.connection;
        String query = "SELECT * FROM first_level_divisions";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            Date created = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");
            Division division = new Division(divisionId, name, created, createdBy, lastUpdate, lastUpdateBy, countryID);
            allDivisions.add(division);
        }
    }

    public Division(){

    }

    @Override
    public String toString(){
        return this.getName();
    }

    public String getName(){
        return this.name;
    }

    public Division(int divisionID, String division, Date create_Date, String created_By, Date last_Update, String last_Updated_By, int country_ID){
        this.Division_ID = divisionID;
        this.Division = division;
        this.Create_Date = create_Date;
        this.Created_By = created_By;
        this.Last_Update = last_Update;
        this.Last_Updated_By = last_Updated_By;
        this.Country_ID = country_ID;
        this.name = division;
    }

}
