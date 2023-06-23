package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.gym_member.Member;
import com.example.globogym.gym_member.UserFormController;
import com.example.globogym.manager.Manager;
import core.ActionLogger;
import core.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateUserForm implements Initializable {
    @FXML
    TextField nameField, surnameField, usernameField, passwordField;
    @FXML
    DatePicker birthDateField;
    @FXML
    ListView<Manager> managerListField;
    @FXML
    Button cancelButton, saveButton;

    private void populateMemberFields() {
        Member member = (Member) LoginController.loggedUser;
        nameField.setText(member.getName());
        surnameField.setText(member.getSurname());
        usernameField.setText(member.getUsername());
        System.out.println(LoginController.loggedUser.username);
        passwordField.setText(member.getPassword());
        birthDateField.setValue(member.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void populateManagerFields() {
        for (Manager m : Manager.managersList) {
            managerListField.getItems().add(m);
        }
        managerListField.setCellFactory(p -> new ListCell<Manager>() {
            @Override
            protected void updateItem(Manager item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getFullName() == null) {
                    setText(null);
                } else {
                    setText(item.getFullName());
                }
            }
        });
    }

    public void saveForm() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String birthDate = birthDateField.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (LoginController.userRole == Role.MEMBER) {
            UserFormController.editUser(LoginController.loggedUser.getId(),username, password, name, surname, birthDate );
        } else {
            Manager selectedManager = managerListField.getSelectionModel().getSelectedItem();
            UserFormController.saveUser(username, password, name, surname, birthDate, selectedManager.getId());
            ActionLogger.setLog("Selected Manager: " + selectedManager.getName());
        }

       ActionLogger.setLog("Form saved!");
    }

    public void cancelForm() {
        ActionLogger.setLog("Form canceled!");
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActionLogger.setLog("initialize userForm");
        switch (LoginController.userRole) {
            case MEMBER -> populateMemberFields();
            case STAFF, MANAGER -> populateManagerFields();
        }
    }
}
