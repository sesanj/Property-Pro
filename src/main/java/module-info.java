module com.example.propertypro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.propertypro to javafx.fxml;
    exports com.example.propertypro;
}