package com.example.globogym.actions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static com.example.globogym.LoginController.allowedActions;

public class ActionListController {

    @FXML
    Label nameLabel;
    @FXML
    VBox actionListRoot;

    ArrayList<Button> actions = new ArrayList<Button>();
    ObservableList<Button> listInstance = FXCollections.observableArrayList();

    public void sayHello(){
        nameLabel.setText("Hello: ");
    }
    public void generateActionListButtons(){
        actionListRoot.setSpacing(10);
        actionListRoot.setPadding(new Insets(10));

        for (String action : allowedActions) {
            Button button = new Button(action);
            actionListRoot.getChildren().add(button);
        }
    }



}
