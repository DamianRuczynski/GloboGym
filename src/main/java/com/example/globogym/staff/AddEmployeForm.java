package com.example.globogym.staff;

import com.example.globogym.core.StaffFormController;
import core.ActionLogger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class AddEmployeForm {
    @FXML
    TextField nameField, surnameField, salaryField, usernameField, passwordField;
    @FXML
    DatePicker birthDateField;
    @FXML
    Button cancelButton, saveButton;

    public void saveForm() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String salary = salaryField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String birthDate = birthDateField.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StaffFormController.saveCoach(username, password, name, surname, birthDate, salary);
        System.out.println("user created");
        ActionLogger.setLog("Form saved! USER CREATED");
    }

    public void cancelForm() {
        System.out.println("form cancelled");
        ActionLogger.setLog("form cancelled");
    }
}
