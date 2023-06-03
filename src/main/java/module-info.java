module com.example.globogym {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.globogym to javafx.fxml;
    exports com.example.globogym;
}