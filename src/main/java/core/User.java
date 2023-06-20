package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class User {

    protected int id;
    public String username;
    private String password;
    Role role;
    private String name;
    private String surname;
    private Date birthdate;

    public User(int id, String username, String password, Role role, String name, String surname, String birthdate) {
        try {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
            this.name = name;
            this.surname = surname;
            this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
            ActionLogger.setLog("user " + this.name + " logged!");
        } catch (ParseException e) {
            ActionLogger.setLog("cannot create user");
        }finally {
//            System.out.println(this.name + " " + this.surname + " born in: " + this.birthdate);
        }
        ;

    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Date getBirthDate() {
        return this.birthdate;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }
}
