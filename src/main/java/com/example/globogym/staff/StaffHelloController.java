package com.example.globogym.staff;

import com.example.globogym.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class StaffHelloController extends HelloController {
    @FXML
    Label nameLabel;

    @Override
    public void displayName(String username) {
        nameLabel.setText("Hello employee: " + username);
    }

    @Override
    public void logout(ActionEvent e) throws IOException {
        super.logout(e);
    }
}
