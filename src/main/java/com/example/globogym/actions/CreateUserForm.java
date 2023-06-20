package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.gym_member.Member;
import com.example.globogym.gym_member.UserFormController;
import com.example.globogym.manager.Manager;
import core.Role;
import core.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
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
        System.out.println(member.getBirthDate());
        birthDateField.setValue(member.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void populateManagerFields() {
        System.out.println("manadzerskie costam");
        for(Manager m : Manager.managersList){
            managerListField.getItems().add(m);
        }
        managerListField.setCellFactory(p -> new ListCell<Manager>(){
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
        if (LoginController.userRole == Role.MEMBER) {
                Member member = (Member) LoginController.loggedUser;
                member.setName(nameField.getText());
                member.setSurname(surnameField.getText());
        } else if (LoginController.userRole == Role.MANAGER) {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            LocalDate birthDate = birthDateField.getValue();
            Manager selectedManager = managerListField.getSelectionModel().getSelectedItem();
            UserFormController.saveUser(username, password, name, surname, birthDate, selectedManager.getId());
            System.out.println("Selected Manager: " + selectedManager.getName());
        }

        System.out.println("Form saved!");
    }

    public void cancelForm() {
        System.out.println("Form canceled!");
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("zaladowany");
        switch (LoginController.userRole) {
            case MEMBER:
                populateMemberFields();
                break;
            case MANAGER:
                populateManagerFields();
                break;
        }
    }
}
