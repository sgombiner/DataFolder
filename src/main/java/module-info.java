module com.example.datafolder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datafolder to javafx.fxml;
    exports com.example.datafolder;
}