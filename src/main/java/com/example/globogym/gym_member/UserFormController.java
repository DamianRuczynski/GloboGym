package com.example.globogym.gym_member;

import core.ActionLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserFormController {
    public static void openEdit(int id) {

        //open modal fot user with given data
    }

    public static void saveUser(String username, String password, String name, String surname, String birthDate, int managerId) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/data/members.txt", true));
            String userDTO = generateMaxId() + "," + username + "," + password + ",member," + name + "," + surname + "," + birthDate + "," + managerId + "," + "100";
            createUserCredentials(generateMaxId(), username, password);
            pw.append("\n" + userDTO);
            ActionLogger.setLog("user created: " + username);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create user");
        }
    }

    private static void createUserCredentials(int generateMaxId, String username, String password) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/data/users.txt", true));
            String userCredentialsDTO = "U" + generateMaxId() + "," + username + "," + password + ",member";
            pw.append("\n" + userCredentialsDTO);
            ActionLogger.setLog("successfully created credentials for user: "+ username);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create user credentials for user: "+ username);
        }
    }

    private static int generateMaxId() {
        return 3;
    }
}
