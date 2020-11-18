package Model;

import java.util.Date;

public class Country {
    private int Country_ID;
    private String Country;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Update_By;
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

    public Country(int countryId, String country, Date createDate, String createBy, Date lastUpdate, String lastUpdateBy) {
        this.Country_ID = countryId;
        this.Country = country;
        this.Create_Date = createDate;
        this.Created_By = createBy;
        this.Last_Update = lastUpdate;
        this.Last_Update_By = lastUpdateBy;
    }

}
