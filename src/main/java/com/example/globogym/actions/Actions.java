package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.gym_member.Member;
import core.ActionLogger;
import core.Role;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.*;

public class Actions {
    public static Map<Role, List<String>> permissions = new HashMap<>();

    static {
        permissions.put(Role.MEMBER, Arrays.asList("showTrainings", "setTraining", "SignOutFromTraining", "showAccount", "PayAccount", "editData", "showStats"));
        permissions.put(Role.STAFF, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats", "createMember", "addTraining", "deleteTraining"));
        permissions.put(Role.MANAGER, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats", "createMember", "addTraining", "deleteTraining", "addEmployee", "editGreetings", "showAllMembers", "showClubStats"));
    }

    public static void setEventOnActionButton(List<String> allowedActions) {
        for (String action : allowedActions) {
            System.out.println(action);
        }
    }

    public static void showTrainings() {
        System.out.println("Showing trainings...");
    }

    public static void setTraining() {
        System.out.println("Setting training...");
    }

    public static void SignOutFromTraining() {
        System.out.println("Signing out from training...");
    }

    public static void showAccount() {
        System.out.println("Showing account...");
    }

    public static void PayAccount() {
        ActionLogger.setLog("account payment is started");
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Pay Account");
        a.setHeaderText("Your account will be active!");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Member) LoginController.loggedUser).payCard();
        } else {
            ActionLogger.setLog("payment of the card is closed");
        }
    }

    public static void editData() {
        System.out.println("Editing data...");
    }

    public static void showStats() {
        System.out.println("Showing stats...");
    }

    public static void createMember() {
        System.out.println("Creating member...");
    }

    public static void addTraining() {
        System.out.println("Adding training...");
    }

    public static void deleteTraining() {
        System.out.println("Deleting training...");
    }

    public static void addEmployee() {
        System.out.println("Adding employee...");
    }

    public static void editGreetings() {
        System.out.println("Editing greetings...");
    }

    public static void showAllMembers() {
        System.out.println("Showing all members...");
    }

    public static void showClubStats() {
        System.out.println("Showing club stats...");
    }

    public static void callMethod(String methodName) {
        try {
            Actions.class.getMethod(methodName).invoke(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

