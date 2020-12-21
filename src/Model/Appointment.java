package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Appointment {
    private int Appointment_ID;
    private int Contact_ID;
    private Date Create_Date;
    private String Created_By;
    private int Customer_ID;
    private String Description;
    private String End;
    private String Start;
    private Date Last_Update;
    private String Last_Updated_By;
    private String Location;
    private String Title;
    private String Type;
    private int User_ID;

    private String CustomerName;
    private String ContactName;
    private String UserName;

    /**
     * create an appointment instance with no params
     */
    public Appointment(){}

    /**
     * create an appointment instance with the provided params
     * @param Appointment_ID set as appointment id
     * @param title set as appointment title
     * @param description set as appointment description
     * @param location set as appointment location
     * @param type set as appointment type
     * @param start set as appointment start
     * @param end set as appointment end
     * @param cusID set as appointment customer id
     */
    public Appointment(int Appointment_ID, String title, String description, String location, String type, String start, String end, int cusID ){
        this.Appointment_ID = Appointment_ID;
        this.Title = title;
        this.Description = description;
        this.Location = location;
        this.Type = type;
        this.Start = start;
        this.End = end;
        this.Customer_ID = cusID;
    }

    /**
     * create an appointment with the provided parameters
     * @param Appointment_ID set as appointment id
     * @param title set as appointment title
     * @param description set as appointment description
     * @param location set as appointment location
     * @param type set as appointment type
     * @param start set as appointment start
     * @param end set as appointment end date
     * @param cusID set as appointment customer id
     * @param contID set as appointment contact id
     */
    public Appointment(int Appointment_ID, String title, String description, String location, String type, String start, String end, int cusID, int contID ){
        this.Appointment_ID = Appointment_ID;
        this.Title = title;
        this.Description = description;
        this.Location = location;
        this.Type = type;
        this.Start = start;
        this.End = end;
        this.Customer_ID = cusID;
        this.Contact_ID = contID;
    }

    /**
     *     create an appointment with the provided parameters
     * @param Appointment_ID set as appointment id
     * @param title set as appointment title
     * @param description set as appointment description
     * @param location set as appointment location
     * @param contactName set as appointment contact name
     * @param contactId set as appointment contact id
     * @param type set as appointment type
     * @param start set as appointment start date
     * @param end set as appointment end date
     * @param customerName set as appointment customer name
     * @param userName set as appointment user name
     * @param cusID set as appointment customer id
     */
    public Appointment(int Appointment_ID, String title, String description, String location, String contactName, int contactId, String type, String start, String end, String customerName, String userName, int cusID ){
        this.Appointment_ID = Appointment_ID;
     this.Title = title;
     this.Description = description;
     this.Location = location;
     this.ContactName = contactName;
     this.Contact_ID = contactId;
     this.Type = type;
     this.Start = start;
     this.End = end;
     this.CustomerName = customerName;
     this.UserName = userName;
     this.Customer_ID = cusID;
    }


    /**
     * create an appointment with the provided parameters
     * @param Appointment_ID set as appointment id
     * @param Contact_ID set as appointment contact id
     * @param Create_Date set as appointment created date
     * @param Created_By set as appointment created by
     * @param Customer_ID set as appointment customer id
     * @param Description set as appointment description
     * @param End set as appointment end date
     * @param Start set as appointment start date
     * @param Last_Update set as appointment last updated date
     * @param Last_Updated_By set as appointment last updated by
     * @param Location set as appointment location
     * @param Title set as appointment title
     * @param Type set as appointment type
     * @param User_ID set as appointment user id
     */
    public Appointment(int Appointment_ID, int Contact_ID, Date Create_Date, String Created_By, int Customer_ID, String Description, String End, String Start, Date Last_Update, String Last_Updated_By, String Location, String Title, String Type, int User_ID ){
        this.Appointment_ID = Appointment_ID;
        this.Contact_ID = Contact_ID;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Customer_ID = Customer_ID;
        this.Description = Description;
        this.End = End;
        this.Start = Start;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Location = Location;
        this.Title = Title;
        this.Type = Type;
        this.User_ID = User_ID;
    }

    /**
     *
     * @return customer name as string
     */
    public String getCustomerName() {
        return this.CustomerName;
    }

    /**
     *
     * @return contact name as string
     */
    public String getContactName() {
        return this.ContactName;
    }

    /**
     *
     * @return user name as string
     */
    public String getUserName() {return this.UserName;}

    /**
     *
     * @return appointment id as int
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     *
     * @param appointment_ID set as appointment id
     */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**
     *
     * @return contact id as int
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     *
     * @param contact_ID
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     *
     * @return created date as Date
     */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /**
     *
     * @param create_Date set as created date
     */
    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

    /**
     *
     * @return return created by as string
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *
     * @param created_By set as created by
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     *
     * @return customer id as int
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     *
     * @param customer_ID set as customer id
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     *
     * @return get appointment description as string
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param description set as appointment description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     *
     * @return appointment end
     */
    public String getEnd() {
        return End;
    }

    /**
     *
     * @param end set as appointment end
     */
    public void setEnd(String end) {
        End = end;
    }

    /**
     *
     * @return start date as string
     */
    public String getStart() { return Start; }

    /**
     *
     * @return last updated date as Date
     */
    public Date getLast_Update() {
        return Last_Update;
    }

    /**
     *
     * @param last_Update is set as last update date
     */
    public void setLast_Update(Date last_Update) {
        Last_Update = last_Update;
    }

    /**
     *
     * @return last updated by as string
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     *
     * @param last_Updated_By set as last updated by
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     *
     * @return location as a string
     */
    public String getLocation() {
        return Location;
    }

    /**
     *
     * @param location set as appointment location
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     *
     * @return appointment titile
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param title set as the appointment title
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param type set as appointment type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     *
     * @return user id as an int
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     *
     * @param user_ID set as user id
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     * calls the db and returns all appointments with provided customer id
     * @param customerId customer id
     * @param conn db connection
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByCustomerId(int customerId, Connection conn) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String query = "select * from appointments where Customer_ID = " + customerId;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("Appointment_ID");
            int cusId = rs.getInt("Customer_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            Appointment newApp = new Appointment(id, title, description, location, type, start, end, cusId);
            appointments.add(newApp);
        }
        return appointments;
    }

    /**
     * calls db and returns all appointments with contact id of the provided contact id
     * @param contactId contact id
     * @param conn db connection
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByContactID(int contactId, Connection conn) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String query = "select * from appointments where Contact_ID = " + contactId;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("Appointment_ID");
            int cusId = rs.getInt("Customer_ID");
            int contId = rs.getInt("Contact_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            Appointment newApp = new Appointment(id, title, description, location, type, start, end, cusId, contId);
            appointments.add(newApp);
        }
        return appointments;
    }

    /**
     * get all appointments using provided db connection
     * @param conn db connection
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments(Connection conn) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String query = "select * from appointments";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int id = rs.getInt("Appointment_ID");
            int cusId = rs.getInt("Customer_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            Appointment newApp = new Appointment(id, title, description, location, type, start, end, cusId);
            appointments.add(newApp);
        }
        return appointments;
    }

}
