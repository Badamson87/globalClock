package Model;

import Helper.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Contact {
    public String name;
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    public Contact(){}

    public Contact(int id, String name, String email){
        this.Contact_ID = id;
        this.Contact_Name = name;
        this.Email = email;
        this.name = name;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    public String getName(){
        return this.name;
    }

    public static Contact getContactById(int Id) throws SQLException {
        Connection con = DBConnect.connection;
        String query = "SELECT * FROM contacts where Contact_ID = '" + Id + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Contact newContact = new Contact();
        while (rs.next()){
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            newContact = new Contact(id, name, email);
        }
        return newContact;
    }
}
