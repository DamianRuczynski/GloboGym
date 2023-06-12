package com.example.globogym.shared.actions;

import core.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actions {
    public static Map<Role, List<String>> permissions = new HashMap<>();

    static {
        permissions.put(Role.MEMBER, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats"));
        permissions.put(Role.STAFF, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats", "createMember", "addTraining", "deleteTraining"));
        permissions.put(Role.MANAGER, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats", "createMember", "addTraining", "deleteTraining", "addEmployee", "editGreetings", "showAllMembers", "showClubStats"));
    }

//    public static void main(String[] args) {
//        List<String> memberActions = permissions.get(Role.MEMBER);
//        System.out.println(memberActions); // [showTrainings, setTraining, showAccount, editData, showStats]
//
//        List<String> staffActions = permissions.get(Role.STAFF);
//        System.out.println(staffActions); // [showTrainings, setTraining, showAccount, editData, showStats, createMember, addTraining, deleteTraining]
//
//        List<String> managerActions = permissions.get(Role.MANAGER);
//        System.out.println(managerActions); // [showTrainings, setTraining, showAccount, editData, showStats, createMember, addTraining, deleteTraining, addEmployee, editGreetings, showAllMembers, showClubStats]
//    }
}
