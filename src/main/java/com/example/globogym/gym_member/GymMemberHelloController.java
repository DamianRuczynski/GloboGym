package com.example.globogym.gym_member;

import com.example.globogym.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class GymMemberHelloController extends HelloController {
    @FXML
    Label nameLabel;

    @Override
    public void displayName(String username) {
        nameLabel.setText("Hello client: " + username);
    }

    @Override
    public void logout(ActionEvent e) throws IOException {
        System.out.println("wyloguj");
//        super.logout(e);
    }
}
