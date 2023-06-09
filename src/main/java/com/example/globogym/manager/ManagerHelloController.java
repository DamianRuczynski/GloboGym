package com.example.globogym.manager;

import core.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ManagerHelloController extends HelloController {
    @FXML
    Label nameLabel;

    @Override
    public void displayName(String username) {
        nameLabel.setText("Hello manager: " + username);
    }

    @Override
    public void logout(ActionEvent e) throws IOException {
        super.logout(e);
    }
}
