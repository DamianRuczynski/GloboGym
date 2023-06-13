package com.example.globogym;

import com.example.globogym.shared.actions.Actions;
import core.ActionLogger;
import core.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.globogym.LoginController.allowedActions;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("GLOBO GYM");
            stage.setScene(scene);
            stage.show();
            ActionLogger.setLog("open application");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
