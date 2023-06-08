package core;

import java.text.SimpleDateFormat;

public abstract class User {
    int id;
    String username;
    String password;
    Role role;
    SimpleDateFormat birthdate;
    String name;
    String surname;

    public User(int id, String username, String password, Role role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;

    }
}
