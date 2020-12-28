package View_Controller;
import Helper.DBConnect;
import Helper.TimeController;
import Model.Appointment;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


/**
 * This class focuses on display of the about page. It also checks on login for upcoming schedule appointments
 */
public class AboutController implements Initializable {
    private static Connection conn;

    /**
     * Checks and displays message for appointments on login.
     */
    private static void appointmentCheck() throws SQLException {
        TimeController timeController = new TimeController();
        String fromDate = timeController.convertToUTC(LocalDateTime.now());
        String toDate = timeController.convertToUTC(LocalDateTime.now().plusMinutes(15));
        String query = "select * from appointments inner join customers on appointments.Customer_ID = customers.Customer_ID inner join contacts on appointments.Contact_ID = contacts.Contact_ID inner join users on appointments.User_ID = users.User_ID where appointments.Start >= '" + fromDate + "' and appointments.Start <= '" +  toDate + "';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next())
        {
            int id = rs.getInt("Appointment_ID");
            int cusId = rs.getInt("Customer_ID");
            int contactId = rs.getInt("Contact_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String contact = rs.getString("Contact_Name");
            String type = rs.getString("Type");
            String start =  rs.getString("Start");
            String end = rs.getString("End");
            String customer = rs.getString("Customer_Name");
            String userName = rs.getString("User_Name");
            Appointment newApp = new Appointment(id, title, description, location, contact, contactId, type, start, end, customer, userName, cusId);
            String notice = "AppointmentID: " + newApp.getAppointment_ID() + " Start:  " + timeController.convertToLocal(newApp.getStart());
            MessageModal.display("Upcoming Scheduled Appointment", notice);
        }
        else{
            MessageModal.display("No Scheduled Appointment", "You have no upcoming scheduled appointments.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DBConnect.connection;
        try {
            this.appointmentCheck();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
