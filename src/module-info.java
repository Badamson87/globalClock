module globalClock {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector;
    requires org.junit.jupiter.api;

    opens View_Controller;
}