package com.example.globogym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
   Label nameLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void displayName(String username){
        nameLabel.setText("Hello: " + username);
    }

    public void logout(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/globogym/login-view.fxml" ));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}