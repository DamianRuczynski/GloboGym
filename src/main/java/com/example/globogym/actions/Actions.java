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
        permissions.put(Role.MEMBER, Arrays.asList("showTrainings", "setTraining", "signOutFromTraining", "showAccount", "payAccount", "editData", "showStats"));
        permissions.put(Role.STAFF, Arrays.asList("showTrainings", "setTraining", "showStats", "createMember", "addTraining", "deleteTraining"));
        permissions.put(Role.MANAGER, Arrays.asList("showTrainings", "setTraining", "showStats", "createMember", "addTraining", "deleteTraining", "addEmployee", "editGreetings", "showAllMembers", "showClubStats"));
    }


    public static void showTrainings() {
        System.out.println("Showing trainings...");
    }

    public static void setTraining() {
        System.out.println("Setting training...");
    }

    public static void signOutFromTraining() {
        System.out.println("Signing out from training...");
    }

    public static void showAccount() {
        System.out.println("Showing account...");
    }

    public static void payAccount() {
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
        ActionLogger.setLog("Editing user data...");
    }

    public static void showStats() {
        System.out.println("Showing stats...");
    }

    public static void createMember() {
        ActionLogger.setLog("Creating new member...");
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

    private static String generateViewFileName(String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append("actions/");
        for (char c : methodName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append('-').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        sb.append("-view.fxml");
        return sb.toString();
    }

    public static String callMethod(String methodName) {
        try {
            Actions.class.getMethod(methodName).invoke(null);
            ActionLogger.setLog("method: " + methodName + " is invoking");
        } catch (Exception e) {
            ActionLogger.setLog("Error: " + e.getMessage());
        }
        return generateViewFileName(methodName);
    }

}

