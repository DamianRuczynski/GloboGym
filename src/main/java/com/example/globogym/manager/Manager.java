package com.example.globogym.manager;

import core.ActionLogger;
import core.ManagementStyle;
import core.Role;
import core.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        super(id, username, password, role);
        try {
            this.name = name;
            this.surname = surname;
            this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
            this.salary = salary;
            this.managementStyle = ManagementStyle.valueOf(managementStyle.toUpperCase());
            managerMessage = message;
        } catch (ParseException e) {
            ActionLogger.setLog("cannot create user");
        }finally {
            System.out.println(this.name + " say: " + managerMessage);
        }
    }
//    public Manager(int id, String username, String password, Role role, String message){
//        super(id, username, password, role);
//        this.managerMessage = message;
//        managersList.add(this);
//        ActionLogger.setLog("Manager created");
//    }

    public static String greetings() {
        return managerMessage;
    }

    public static Manager getManagerById(int managerId) {
        return (managerId < managersList.size() && managerId > 0) ? managersList.get(managerId) : null; // so propably when in database of user manger id will be "ko" ot returns 0 as an index. it can be ok
    }


}
