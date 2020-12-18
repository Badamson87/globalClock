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

    /**
     * creates a contact instance with no params
     */
    public Contact(){}

    /**
     * creates a contact instance with provides params
     * @param id set as contact id
     * @param name set as contact name
     * @param email set as contact email
     */
    public Contact(int id, String name, String email){
        this.Contact_ID = id;
        this.Contact_Name = name;
        this.Email = email;
        this.name = name;
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
     * @param contact_ID sets the contact id as provided id
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     *
     * @return contact name as string
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     *
     * @param contact_Name set as contact name
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     *
     * @return contact email as string
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param email set as contact email
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     *
     * @return contact name as name for combo box
     */
    @Override
    public String toString(){
        return this.getName();
    }

    /**
     *
     * @return contact name for overirde
     */
    public String getName(){
        return this.name;
    }

    /**
     * querys the db and returns a contact with provided contact id
     * @param Id contact id
     * @return returns a contact with provided id
     * @throws SQLException
     */
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
