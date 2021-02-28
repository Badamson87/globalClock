package Model;

import java.util.Date;

public class Location {
    public String name;
    public Date Create_Date;
    public String Created_By;
    public Date Last_Update;
    public String Last_Update_By;

    public Location(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Location(String name, Date create_Date, String created_by, Date last_update, String last_Update_By) {
        this.name = name;
        this.Create_Date = create_Date;
        this.Created_By = created_by;
        this.Last_Update = last_update;
        this.Last_Update_By = last_Update_By;
    }

}
