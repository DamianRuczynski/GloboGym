package com.example.globogym.manager;

import core.ActionLogger;
import core.ManagementStyle;
import core.Role;
import core.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Manager extends User {
    public static ArrayList<Manager> managersList = new ArrayList<>();
    public static String managerMessage;
    private String name;
    private String surname;
    private Date birthdate;
    private int salary;
    private ManagementStyle managementStyle;

    public Manager(int id, String username, String password, Role role, String name, String surname, String birthdate, int salary, String managementStyle, String message) {
        super(id, username, password, role, name, surname, birthdate);
        this.salary = salary;
        this.managementStyle = ManagementStyle.valueOf(managementStyle.toUpperCase());
        managerMessage = message;
    }

    public static String greetings() {
        return managerMessage;
    }

    public static Manager getManagerById(int managerId) {
        generateManagersList();
        return (managerId < managersList.size() && managerId > 0) ? managersList.get(managerId) : managersList.get(0); // so propably when in database of user manger id will be "ko" ot returns 0 as an index. it can be ok
    }

    public static void generateManagersList(){
        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/managers.txt"));
            String line;
            while((line = input.readLine()) != null){
                String[] credentials = line.split(",");
                Manager m = new Manager(Integer.parseInt(credentials[0]), credentials[1], credentials[2], Role.MANAGER, credentials[3], credentials[4], credentials[5], Integer.parseInt(credentials[6]), credentials[7],credentials[8]);
                managersList.add(m);
            }
        } catch (FileNotFoundException e) {
            ActionLogger.setLog("an error occured while readin managers - file not found");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            ActionLogger.setLog("somehow mangerlist is empty");
            System.out.println(e.getMessage());
        }
    }


}
