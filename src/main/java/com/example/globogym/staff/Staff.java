package com.example.globogym.staff;

import core.Role;
import core.User;

public class Staff extends User {
    public Staff(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }
}
