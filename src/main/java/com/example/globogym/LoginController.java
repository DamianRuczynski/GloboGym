package com.example.globogym;

import com.example.globogym.actions.Actions;
import com.example.globogym.gym_member.Member;
import com.example.globogym.manager.Manager;
import com.example.globogym.staff.Staff;
import core.ActionLogger;
import com.example.globogym.core.HelloController;
import core.Role;
import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.List;

public class LoginController {
    @FXML
    TextField nameField, passwordField;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public static User loggedUser;

    public static Role userRole;
    public static List<String> allowedActions;



    public void login(ActionEvent e) throws IOException {

        String username = nameField.getText();
        String password = passwordField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(checkUser(username, password) ? "hello-view.fxml" : "invalid-data-view.fxml"));
        root = loader.load();
        ActionLogger.setLog("initial scene loaded");
        if (checkUser(username, password)) {
            loggedUser = readUser(username, password, String.valueOf(userRole).toLowerCase());
            HelloController controller = loader.getController();
            controller.displayName(username);
            allowedActions = Actions.permissions.get(userRole);
            controller.generateActionListButtons(allowedActions);
            controller.loadOutlet(loadUserRoleScene());
        }
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

    private User readUser(String username, String password, String file) {
        try{
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/"+file+"s.txt"));
            String line;
            while((line = input.readLine()) != null){
                String[] credentials = line.split(",");
                if (credentials[1].equals(username) && credentials[2].equals(password)) {
                    ActionLogger.setLog("User successfully loaded");
                    return switch (userRole) {
                        case STAFF -> new Staff(Integer.parseInt(credentials[0]), credentials[1], credentials[2], userRole, credentials[3], credentials[4], credentials[5], Integer.parseInt(credentials[6]));
                        case MEMBER -> new Member(Integer.parseInt(credentials[0]), credentials[1], credentials[2], userRole, credentials[4], credentials[5], credentials[6], credentials[7], credentials[8]);
                        case MANAGER -> new Manager(Integer.parseInt(credentials[0]), credentials[1], credentials[2], userRole, credentials[3], credentials[4], credentials[5], Integer.parseInt(credentials[6]), credentials[7],credentials[8]);
                    };
                }
            }
            return null;
        }catch (FileNotFoundException e) {
            System.out.println("file not found");
            ActionLogger.setLog("file not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("an exception occurs while reading users!");
            ActionLogger.setLog("problem with reading users");
            throw new RuntimeException(e);
        }

    }
    private boolean checkUser(String username, String password) {
        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/users.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[1].equals(username) && credentials[2].equals(password)) {
                    userRole = Role.valueOf(credentials[3].toUpperCase());
                    ActionLogger.setLog("user " + username + " is logged");
                    return true;
                }
            }
            ActionLogger.setLog("cannot find user with given username: " + username);
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            ActionLogger.setLog("file not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("an exception occurs while reading users!");
            ActionLogger.setLog("problem with reading users");
            throw new RuntimeException(e);
        }
    }
}
