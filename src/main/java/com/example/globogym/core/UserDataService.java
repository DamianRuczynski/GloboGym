package com.example.globogym.core;

import com.example.globogym.gym_member.Member;
import com.example.globogym.manager.Manager;
import core.ActionLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataService {

    public static void saveManagers(){
        System.out.println("savaeeeeee");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data/managers.txt"))) {
            for (Manager m : Manager.managersList) {
                String line = m.getId() + "," +
                        m.getUsername() + "," +
                        m.getPassword() + "," +
                        m.getName() + "," +
                        m.getSurname() + "," +
                        m.getBirthDateString() + "," +
                        m.getSalary()+","+
                        m.getManagementStyle() + "," +
                        m.greetings();

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            ActionLogger.setLog("error occurdd while saving users");
        }
    }
}
