package Helper;

import Model.Country;

import java.sql.*;

public class DBConnect {

    private static final String databaseName = "WJ05gTi";
    private static final String DB_URL = "jdbc:mysql://wgudb.ucertify.com:3306/" + databaseName;
    private static final String username = "U05gTi";
    private static final String password = "53688499227";
    private static final String driver = "com.mysql.jdbc.Driver";
    public static Connection connection;

    public static Connection establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
         connection = DriverManager.getConnection(DB_URL, username, password);
        System.out.print("Connected");
        Country.setAllCountries();
        return  connection;
    }
}
