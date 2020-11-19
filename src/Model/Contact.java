package Model;

public class Contact {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    public Contact(){}

    public Contact(int id, String name, String email){
        this.Contact_ID = id;
        this.Contact_Name = name;
        this.Email = email;
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
}
