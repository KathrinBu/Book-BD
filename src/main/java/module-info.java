module com.example.bookbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bookbd to javafx.fxml;
    exports com.example.bookbd;
}