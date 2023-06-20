package com.example.globogym.staff;

import core.ActionLogger;
import core.ManagementStyle;
import core.Role;
import core.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Staff extends User {
    private String name;
    private String surname;
    private Date birthdate;
    private int salary;

    public Staff(int id, String username, String password, Role role, String name, String surname, String birthdate, int salary) {
        super(id, username, password, role, name, surname, birthdate);
        this.salary = salary;
    }
//    public Staff(int id, String username, String password, Role role) {
//        super(id, username, password, role);
//    }
}
