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
 * This class focuses on first level division and the data modeling associated to them.
 */
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

    /**
     *
     * @return division id
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     *
     * @param division_ID set as division id
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     *
     * @return get division name
     */
    public String getDivision() {
        return Division;
    }

    /**
     *
     * @param division set division name
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     *
     * @return get create date
     */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /**
     *
     * @param create_Date set as create date
     */
    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

    /**
     *
     * @return get created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *
     * @param created_By set created by
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     *
     * @return as get last update
     */
    public Date getLast_Update() {
        return Last_Update;
    }

    /**
     *
     * @param last_Update set last update as date
     */
    public void setLast_Update(Date last_Update) {
        Last_Update = last_Update;
    }

    /**
     *
     * @return get last updated by as string
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     *
     * @param last_Updated_By set last updated by
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     *
     * @return get country id as int
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     *
     * @param country_ID set country id
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     *
     * @return returns a list of all divisions
     */
    public static ObservableList<Division> getAllDivisions(){
        return  allDivisions;
    }

    /**
     *
     * @param Id provides a division id
     * @return returns a division with the id
     * @throws SQLException
     */
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

    /**
     *  gets all divisions from db and loads them to allDivisions
     * @throws SQLException
     */
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

    /**
     * empty constructor for divisions
     */
    public Division(){

    }

    /**
     * allows combo boxes to return division name from set division
     * @return
     */
    @Override
    public String toString(){
        return this.getName();
    }

    /**
     *
     * @return returns the name of a division
     */
    public String getName(){
        return this.name;
    }

    /**
     * creates a division with params
     * @param divisionID provides division id
     * @param division provides division name
     * @param create_Date provides division created date
     * @param created_By provides division created by
     * @param last_Update provides division last updated date
     * @param last_Updated_By provides division last updated by
     * @param country_ID provides division country id
     */
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
