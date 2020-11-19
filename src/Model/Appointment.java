package Model;

import java.util.Date;

public class Appointment {
    private int Appointment_ID;
    private int Contact_ID;
    private Date Create_Date;
    private String Created_By;
    private int Customer_ID;
    private String Description;
    private Date End;
    private Date Start;
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

    public Appointment(int Appointment_ID, String title, String description, String location, String contactName, String type, Date start, Date end, String customerName, String userName ){
     this.Appointment_ID = Appointment_ID;
     this.Title = title;
     this.Description = description;
     this.Location = location;
     this.ContactName = contactName;
     this.Type = type;
     this.Start = start;
     this.End = end;
     this.CustomerName = customerName;
     this.UserName = userName;
    }

    public Appointment(int Appointment_ID, int Contact_ID, Date Create_Date, String Created_By, int Customer_ID, String Description, Date End, Date Start, Date Last_Update, String Last_Updated_By, String Location, String Title, String Type, int User_ID ){
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

    public Date getEnd() {
        return End;
    }

    public void setEnd(Date end) {
        End = end;
    }

    public Date getStart() {
        return Start;
    }

    public void setStart(Date start) {
        Start = start;
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
}
