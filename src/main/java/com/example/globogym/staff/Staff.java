package com.example.globogym.staff;

import com.example.globogym.actions.AllMembersList;
import core.Role;
import core.User;

public class Staff extends User {
    private int salary;

    public Staff(int id, String username, String password, Role role, String name, String surname, String birthdate, int salary) {
        super(id, username, password, role, name, surname, birthdate);
        this.salary = salary;
    }

    public static Staff getCoachById(int coachId) {
        return AllMembersList.staffList.get(coachId);
    }
}
