package com.example.globogym.staff;

import core.HelloController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StaffHelloController extends HelloController {
    @FXML
    Label nameLabel;

    @Override
    public void displayName(String username) {
        nameLabel.setText("Hello employee: " + username);
    }

}
