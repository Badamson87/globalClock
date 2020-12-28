package Helper;
import Model.Country;
import Model.Division;
import java.sql.*;

/**
 * This class creates and distributes the database connection for c-195
 */
public class DBConnect {

    private static final String databaseName = "WJ05gTi";
    private static final String DB_URL = "jdbc:mysql://wgudb.ucertify.com:3306/" + databaseName;
    private static final String username = "U05gTi";
    private static final String password = "53688499227";
    private static final String driver = "com.mysql.jdbc.Driver";
    public static Connection connection;

    /**
     *  Establish connection to database
     * @return a db connection is returned
     * @throws ClassNotFoundException
     * @throws SQLException thrown sql exception
     */
    public static Connection establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
         connection = DriverManager.getConnection(DB_URL, username, password);
        System.out.print("Connected");
        Country.setAllCountries();
        Division.setAllDivisions();
        return  connection;
    }
}
