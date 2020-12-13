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

    public Appointment(){}

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

    public String getCustomerName() {
        return this.CustomerName;
    }
    public String getContactName() {
        return this.ContactName;
    }
    public String getUserName() {return this.UserName;}

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
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

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getStart() { return Start; }

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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

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
