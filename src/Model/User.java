package Model;
import java.util.Date;

/**
 * This class focuses on users and the data modeling associated to them.
 */
public class User {
    private int User_ID;
    private String User_Name;
    private String Password;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Update_By;

    /**
     * Create a user with no params
     */
    public User(){
    }

    /**
     * @return Returns a users ID
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     *
     * @param user_ID is set as user id
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     *
     * @return return a users name
     */
    public String getUser_Name() {
        return User_Name;
    }

    /**
     *
     * @param user_Name is set as a users name
     */
    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    /**
     *
     * @return a users password
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param password is set as user password
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     *
     * @return user created date
     */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /**
     *
     * @param create_Date set user created date
     */
    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

    /**
     *
     * @return get user created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *
     * @param created_By is set as user created by
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     *
     * @return get last updated date
     */
    public Date getLast_Update() {
        return Last_Update;
    }

    /**
     *
     * @param last_Update set last updated date
     */
    public void setLast_Update(Date last_Update) {
        Last_Update = last_Update;
    }

    /**
     *
     * @return user last updated by
     */
    public String getLast_Update_By() {
        return Last_Update_By;
    }

    /**
     *
     * @param last_Update_By set as last updated by for user
     */
    public void setLast_Update_By(String last_Update_By) {
        Last_Update_By = last_Update_By;
    }

    /**
     * Create a new user with user params
     * @param userId user id
     * @param userName user username
     * @param pass user password
     * @param createDate user created date
     * @param createBy user created by
     * @param lastUpdate user last update
     * @param lastUpdateBy user last updated by
     */
    public User(int userId, String userName, String pass, Date createDate, String createBy, Date lastUpdate, String lastUpdateBy){
        this.User_ID = userId;
        this.User_Name = userName;
        this.Password = pass;
        this.Create_Date = createDate;
        this.Created_By = createBy;
        this.Last_Update = lastUpdate;
        this.Last_Update_By = lastUpdateBy;
    }
}
