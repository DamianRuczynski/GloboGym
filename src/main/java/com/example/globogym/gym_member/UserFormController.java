package com.example.globogym.gym_member;

import java.time.LocalDate;

public class UserFormController {
    public static void openEdit(int id) {

        //open modal fot user with given data
    }

    public static void saveUser(String username, String password, String name, String surname, LocalDate birthDate, int id) {
        System.out.println("Date: " +birthDate);
        System.out.println("SAVE USER");
    }
}
