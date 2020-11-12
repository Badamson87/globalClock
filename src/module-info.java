module globalClock {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector;

    opens View_Controller;
}