package com.example.globogym;

import com.example.globogym.shared.actions.Actions;
import core.HelloController;
import core.Role;
import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class LoginController {
    @FXML
    TextField nameField, passwordField;

    @FXML
    VBox actionListRoot;
    private Stage stage;
    private Scene scene;
    private Parent root;

    User user;

    public static Role userRole;
    public static List<String> allowedActions;
    public void generateActionListButtons(){
        actionListRoot.setSpacing(10);
        actionListRoot.setPadding(new Insets(10));

        for (String action : allowedActions) {
            Button button = new Button(action);
            actionListRoot.getChildren().add(button);
        }
    }


    public void login(ActionEvent e) throws IOException {

        String username = nameField.getText();
        String password = passwordField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(checkUser(username, password) ? loadUserRoleScene() : "invalid-data-view.fxml"));
        root = loader.load();
        if (checkUser(username, password)) {
            HelloController controller = loader.getController();
            controller.displayName(username);
        }
        allowedActions = Actions.permissions.get(userRole);
        System.out.println(allowedActions);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToHomeScreen(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private String loadUserRoleScene() {
        return switch (userRole) {
            case STAFF -> "staff-member-view.fxml";
            case MEMBER -> "gym-member-view.fxml";
            case MANAGER -> "manager-view.fxml";
        };
    }

    private boolean checkUser(String username, String password) {
        boolean userValid = false;
        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/users.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[1].equals(username) && credentials[2].equals(password)) {
                    userRole = Role.valueOf(credentials[3].toUpperCase());
                    userValid = true;
                }
            }

            return userValid;
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("an exception occurs while reading users!");
            throw new RuntimeException(e);
        }
    }
}
