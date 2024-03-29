package com.example.globogym.core;

import com.example.globogym.MainApplication;
import com.example.globogym.LoginController;
import com.example.globogym.actions.Actions;
import com.example.globogym.gym_member.Member;
import com.example.globogym.manager.Manager;
import core.ActionLogger;
import core.Role;
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

import static com.example.globogym.LoginController.userRole;

public class HelloController {
    @FXML
    Label nameLabel, greetingsLabel;
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
        nameLabel.setText("Hello: " + (LoginController.loggedUser.getName()));
    }

    public void showMessage() {
        greetingsLabel.setText(userRole == Role.MEMBER ? ((Member) LoginController.loggedUser).getManager().greetings() : "");
    }

    public void generateActionListButtons(List<String> allowedActions) {
        showMessage();
        actionListRoot.setSpacing(10);
        actionListRoot.setPadding(new Insets(10));

        for (String action : allowedActions) {
            Button button = new Button(buildName(action));
            button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 3px;");
            button.prefWidthProperty().bind(actionListRoot.widthProperty());
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ActionLogger.setLog("execute: " + action);
                    loadOutlet(Actions.callMethod(action));
                }
            });
            actionButtons.add(button);
            actionListRoot.getChildren().add(button);
        }
//        setTimeout(() -> System.out.println("testowy setTimeout"), 1000);

        Button homeButton = new Button("HOME");
        homeButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px; -fx-background-radius: 3px;");
        homeButton.prefWidthProperty().bind(actionListRoot.widthProperty());
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadOutlet(loadUserHomeView());
            }
        });
        actionButtons.add(homeButton);
        actionListRoot.getChildren().add(homeButton);
    }

    private String loadUserHomeView() {
        return switch (userRole) {
            case STAFF -> "staff-member-view.fxml";
            case MEMBER -> "gym-member-view.fxml";
            case MANAGER -> "manager-view.fxml";
        };
    }

    private String buildName(String action) {
        String[] words = action.split("(?=[A-Z])");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.substring(0, 1).toUpperCase());
            result.append(word.substring(1).toLowerCase());
            result.append(" ");
        }
        return result.toString().trim();
    }


    public void loadOutlet(String userRoleScene) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(userRoleScene));
            Node newContent = loader.load();
            outlet.setCenter(newContent);
            outlet.setAlignment(outlet, Pos.CENTER);/// in future use this method also for load proper view depends on button clik
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent e) throws IOException {
        LoginController.loggedUser = null;
        Manager.managersList = null;
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        root = loader.load();
        ActionLogger.setLog("user logout");
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                ActionLogger.setLog("cannot invoke thread");
            }
        }).start();
    }
}