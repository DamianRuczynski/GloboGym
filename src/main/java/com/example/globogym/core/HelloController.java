package com.example.globogym.core;

import com.example.globogym.MainApplication;
import com.example.globogym.LoginController;
import com.example.globogym.actions.Actions;
import core.ActionLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    @FXML
    Label nameLabel, greetings;
    @FXML
    VBox actionListRoot;
    @FXML
    String userView;
    @FXML
    BorderPane outlet;
    @FXML
    Pane myPane;

    public static ObservableList<Button> actionButtons = FXCollections.observableArrayList();
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void displayName(String username) {
        nameLabel.setText("Hello: " + username);
    }

//    public void showMessage(User user){
//
//        greetings.setText(LoginController.loggedUser instanceof Member ? Manager.greetings() : "");
//    }

    public void generateActionListButtons(List<String> allowedActions) {
        actionListRoot.setSpacing(10);
        actionListRoot.setPadding(new Insets(10));

        for (String action : allowedActions) {
            Button button = new Button(action);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ActionLogger.setLog( "execute: " + action);
                    loadOutlet(Actions.callMethod(action));
                }
            });
            actionButtons.add(button);
            actionListRoot.getChildren().add(button);
        }
        setTimeout(() -> System.out.println("test"), 1000);
    }


    public void loadOutlet(String userRoleScene) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(userRoleScene));
            System.out.println(MainApplication.class.getResource(userRoleScene));
            Node newContent = loader.load();
            outlet.setCenter(newContent);
            outlet.setAlignment(outlet, Pos.CENTER);/// in future use this method also for load proper view depends on button clik
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent e) throws IOException {
        LoginController.loggedUser = null;
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        root = loader.load();
        ActionLogger.setLog("user logout");
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}