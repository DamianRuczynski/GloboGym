package com.example.globogym.manager;

import com.example.globogym.core.HelloController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagerHelloController extends HelloController {
    @FXML
    Label nameLabel;

    @Override
    public void displayName(String username) {
        nameLabel.setText("Hello manager: " + username);
    }

}
