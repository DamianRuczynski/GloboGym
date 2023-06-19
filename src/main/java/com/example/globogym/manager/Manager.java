package com.example.globogym.manager;

import core.ActionLogger;
import core.Role;
import core.User;

import java.util.ArrayList;

public class Manager extends User {
    public static ArrayList<Manager> managersList = new ArrayList<>();
    public static String managerMessage;
    public Manager(int id, String username, String password, Role role, String message){
        super(id, username, password, role);
        this.managerMessage = message;
        managersList.add(this);
        ActionLogger.setLog("Manager created");
    }

    public static String greetings() {
        return managerMessage;
    }

    public static Manager getManagerById(int managerId) {
        return (managerId < managersList.size() && managerId > 0) ?  managersList.get(managerId) : null; // so propably when in database of user manger id will be "ko" ot returns 0 as an index. it can be ok
    }


}
