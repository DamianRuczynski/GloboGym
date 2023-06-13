package com.example.globogym.manager;

import core.Role;
import core.User;

public class Manager extends User {
    public static String managerMessage;
    public Manager(int id, String username, String password, Role role, String message){
        super(id, username, password, role);
        this.managerMessage = message;
    }

    public static String greetings() {
        return managerMessage;
    }
}
