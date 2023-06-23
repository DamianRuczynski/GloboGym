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
        permissions.put(Role.MEMBER, Arrays.asList("enterTraining", "showTrainings", "signOutFromTraining", "showAccount", "editData", "showStats"));
        permissions.put(Role.STAFF, Arrays.asList("createMember", "addTraining", "setStaffToTraining", "deleteTraining", "assignMember", "deleteMember"));
        permissions.put(Role.MANAGER, Arrays.asList("createMember", "assignMember", "deleteMember", "addEmployee", "editGreetings", "showAllMembers", "showClubStats", "trackActivity"));
    }

//MEMBER
    public static void enterTraining() {
        ActionLogger.setLog("Entering training...");
    }

    public static void showTrainings() {
        ActionLogger.setLog("Showing trainings...");
    }

    public static void signOutFromTraining() {
        ActionLogger.setLog("Signing out from training...");
    }

    public static void showAccount() {
        ActionLogger.setLog("Showing account...");
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
        ActionLogger.setLog("Showing stats...");
    }



    //STAFF

    public static void addTraining() {
        ActionLogger.setLog("Adding training...");
    }

    public static void setStaffToTraining() {
        ActionLogger.setLog("Looking for free staff...");
    }

    public static void deleteTraining() {
        ActionLogger.setLog("Deleting training...");
    }


    //MANAGER


    public static void createMember() {
        ActionLogger.setLog("Creating new member...");
    }

    public static void assignMember() {
        ActionLogger.setLog("Assigning member...");
    }

    public static void deleteMember() {
        ActionLogger.setLog("Deleting member...");
    }

    public static void addEmployee() {
        ActionLogger.setLog("Adding employee...");
    }

    public static void editGreetings() {
        ActionLogger.setLog("Editing greetings...");
    }

    public static void showAllMembers() {
        ActionLogger.setLog("Showing all members...");
    }

    public static void showClubStats() {
        ActionLogger.setLog("Showing club stats...");
    }

    public static void trackActivity() {
        ActionLogger.setLog("Tracking activity...");
    }

    //OTHERS
//    public static void assignTraining() {
//        System.out.println("Assiging training..");
//    }
//

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

