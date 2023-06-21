package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.core.UserDataService;
import com.example.globogym.manager.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EditManager implements Initializable {
    @FXML
    TextArea editGreetingField;
    @FXML
    Button editGreetingButton;


    public void editManager(ActionEvent actionEvent) {
        int id = LoginController.loggedUser.getId();
        for(Manager m : Manager.managersList){
            if(m.getId() == id){
                m.setGreeting(editGreetingField.getText());
                System.out.println(m.greetings());
            }
        }

        UserDataService.saveManagers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editGreetingField.setText(((Manager)LoginController.loggedUser).greetings());
    }
}
