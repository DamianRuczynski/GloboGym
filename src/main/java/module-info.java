module com.example.globogym {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.globogym to javafx.fxml;
    exports com.example.globogym;
    exports core;
    opens core to javafx.fxml;
    exports com.example.globogym.manager;
    opens com.example.globogym.manager to javafx.fxml;
    exports com.example.globogym.gym_member;
    opens com.example.globogym.gym_member to javafx.fxml;
    exports com.example.globogym.staff;
    opens com.example.globogym.staff to javafx.fxml;
    exports com.example.globogym.training;
    opens com.example.globogym.training to javafx.fxml;
    exports com.example.globogym.actions;
    opens com.example.globogym.actions to javafx.fxml;
    exports com.example.globogym.core;
    opens com.example.globogym.core to javafx.fxml;
}