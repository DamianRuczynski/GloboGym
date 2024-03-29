package com.example.globogym;

import com.example.globogym.core.TrainingController;
import com.example.globogym.gym_member.Member;
import core.ActionLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainApplication extends Application {
    public static HashMap<Integer, Integer> rooms = new HashMap<>();

    public static HashMap<Integer, ArrayList<Member>> trainingsWithMembers = TrainingController.generateTrainingsAssigning();
    public static void initializeRooms() {
        rooms.put(10, 10);
        rooms.put(11, 8);
        rooms.put(12, 20);
        rooms.put(14, 14);
        rooms.put(17, 3);
    }

    public static int getRoomCapacity(int roomId){
        return rooms.get(roomId);
    }
    @Override
    public void start(Stage stage){
        initializeRooms();
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
