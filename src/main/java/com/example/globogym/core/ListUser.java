package com.example.globogym.core;

import com.example.globogym.gym_member.Member;
import com.example.globogym.manager.Manager;
import com.example.globogym.staff.Staff;
import core.ActionLogger;
import core.Role;
import core.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListUser {
    public static ArrayList generateList(Role role) {
        ArrayList list = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/" + String.valueOf(role).toLowerCase() + "s.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] credentials = line.split(",");
                User user = switch (role) {
                    case STAFF ->
                            new Staff(Integer.parseInt(credentials[0]), credentials[1], credentials[2], Role.STAFF, credentials[3], credentials[4], credentials[5], Integer.parseInt(credentials[6]));
                    case MEMBER ->
                            new Member(Integer.parseInt(credentials[0]), credentials[1], credentials[2], Role.MEMBER, credentials[4], credentials[5], credentials[6], credentials[7], credentials[8]);
                    case MANAGER ->
                            new Manager(Integer.parseInt(credentials[0]), credentials[1], credentials[2], Role.MANAGER, credentials[3], credentials[4], credentials[5], Integer.parseInt(credentials[6]), credentials[7], credentials[8]);
                };
                list.add(user);
            }

        } catch (FileNotFoundException e) {
            ActionLogger.setLog("An error occurred while reading " + role + " - file not found");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            ActionLogger.setLog("Somehow " + role + " is empty");
            System.out.println(e.getMessage());
        }
        return list;
    }
}
