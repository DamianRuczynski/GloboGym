package com.example.globogym.actions;

import core.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actions {
    public static Map<Role, List<String>> permissions = new HashMap<>();

    static {
        permissions.put(Role.MEMBER, Arrays.asList("showTrainings", "setTraining","SignOutFromTraining", "showAccount","PayAccount", "editData", "showStats"));
        permissions.put(Role.STAFF, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats", "createMember", "addTraining", "deleteTraining"));
        permissions.put(Role.MANAGER, Arrays.asList("showTrainings", "setTraining", "showAccount", "editData", "showStats", "createMember", "addTraining", "deleteTraining", "addEmployee", "editGreetings", "showAllMembers", "showClubStats"));
    }
}
