package Helper;

import java.sql.*;

public class DBConnect {

    private static final String databaseName = "WJ05gTi";
    private static final String DB_URL = "jdbc:mysql://wgudb.ucertify.com:3306/" + databaseName;
    private static final String username = "U05gTi";
    private static final String password = "53688499227";
    private static final String driver = "com.mysql.jdbc.Driver";
    public static Connection connection;

    public static void establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
      connection = DriverManager.getConnection(DB_URL, username, password);
      System.out.print("Connected");
        String query = "SELECT * FROM customers";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.print(rs);




    }
}
