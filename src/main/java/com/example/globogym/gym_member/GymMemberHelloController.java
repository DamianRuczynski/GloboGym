package com.example.globogym.gym_member;

import com.example.globogym.core.HelloController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GymMemberHelloController extends HelloController {
    @FXML
    Label nameLabel;

    @Override
    public void displayName(String username) {
        nameLabel.setText("Hello client: " + username);
    }

}
