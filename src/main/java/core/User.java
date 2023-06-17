package core;

import java.text.SimpleDateFormat;

public abstract class User {
    protected int id;
    String username;
    String password;
    Role role;

    public User(int id, String username, String password, Role role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;

    }

}
