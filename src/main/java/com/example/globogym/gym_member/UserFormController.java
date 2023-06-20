package com.example.globogym.gym_member;

import core.ActionLogger;
import core.Role;

import java.io.*;
import java.util.ArrayList;

public class UserFormController {
    private static String FILE_URL = "src/main/data/members.txt";

    public static void openEdit(int id) {

        //open modal fot user with given data
    }

    public static void saveUser(String username, String password, String name, String surname, String birthDate, int managerId) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_URL, true));
            String userDTO = generateMaxId() + "," + username + "," + password + ",member," + name + "," + surname + "," + birthDate + "," + managerId + "," + "100";
            createUserCredentials(username, password);
            pw.append("\n" + userDTO);
            ActionLogger.setLog("user created: " + username);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create user");
        }
    }

    private static void createUserCredentials(String username, String password) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/data/users.txt", true));
            String userCredentialsDTO = "U" + generateMaxId() + "," + username + "," + password + ",member";
            pw.append("\n" + userCredentialsDTO);
            ActionLogger.setLog("successfully created credentials for user: " + username);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create user credentials for user: " + username);
        }
    }

    private static int generateMaxId() {
        int maxId = 0;
        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/members.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] credentials = line.split(",");
                maxId = Integer.parseInt(credentials[0]);
            }
            return maxId + 1;
        } catch (IOException e) {
            ActionLogger.setLog("an exception occurs while generating id");
            throw new RuntimeException(e);
        }
    }

    public static void editUser(int id, String username, String password, String name, String surname, String birthDate) {
        ArrayList<Member> userList = readUserListFromFile(FILE_URL);
        for (Member user : userList) {
            if (user.getId() == id) {
                user.setUsername(username);
                user.setPassword(password);
                user.setName(name);
                user.setSurname(surname);
                System.out.println(birthDate);
                user.setBirthDate(birthDate);
                createUserCredentials(user.getUsername(), user.getPassword());
                ActionLogger.setLog("user data successfully changed: " + user.getUsername());
                break;
            }
        }
        writeUserListToFile(userList);
    }

    private static void writeUserListToFile(ArrayList<Member> userList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_URL))) {
            for (Member m : userList) {
                String line = m.getId() + "," +
                        m.getUsername() + "," +
                        m.getPassword() + ",member," +
                        m.getName() + "," +
                        m.getSurname() + "," +
                        m.getBirthDateString() + "," +
                        m.getManagerId() + "," + m.getAccount();

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            ActionLogger.setLog("error occurdd while saving users");
        }
    }

    private static ArrayList<Member> readUserListFromFile(String filename) {
        ArrayList<Member> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                int id = Integer.parseInt(userData[0]);
                String username = userData[1];
                String password = userData[2];
                String name = userData[4];
                String surname = userData[5];
                String birthDate = userData[6];
                String managerId = userData[7];
                userList.add(new Member(id, username, password, Role.MEMBER, name, surname, birthDate, managerId, userData[8]));
            }
        } catch (IOException e) {
            ActionLogger.setLog("cannot read users!");
        }

        return userList;
    }
}
