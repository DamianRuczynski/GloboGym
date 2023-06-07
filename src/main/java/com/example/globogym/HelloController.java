package com.example.globogym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
   Label nameLabel;


    public void displayName(String username){
        nameLabel.setText("Hello: " + username);
    }

}