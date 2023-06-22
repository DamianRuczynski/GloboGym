package com.example.globogym.core;

import core.ActionLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.globogym.gym_member.UserFormController.generateMaxId;

public class StaffFormController {
    private static String FILE_URL = "src/main/data/staffs.txt";
    public static void saveCoach(String username, String password, String name, String surname, String birthDate, String salary) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_URL, true));
            int userId = generateMaxId("staffs");
            String userDTO = userId + "," + username + "," + password + "," + name + "," + surname + "," + birthDate + "," + salary;
            createStaffCredentials(username, password);
            pw.append("\n" + userDTO);
            ActionLogger.setLog("staff created: " + username);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create staff");
        }
    }

    private static void createStaffCredentials(String username, String password) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/data/users.txt", true));
            String staffCredentialsDTO = "S" + generateMaxId("staffs") + "," + username + "," + password + ",staff";
            pw.append("\n" + staffCredentialsDTO);
            ActionLogger.setLog("successfully created credentials for staff: " + username);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create user credentials for staff: " + username);
        }
    }
}
