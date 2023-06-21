package core;

import com.example.globogym.core.Identifiable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class User implements Identifiable {

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
        } finally {
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
    public void setId(int id) {this.id = id;}


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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    public void setBirthDate(String birthdate) {
        try {
            this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
            System.out.print("Z USERA ");
            System.out.println(this.birthdate);
        } catch (ParseException e) {
            ActionLogger.setLog("error occurs while parsing data");
        }
    }

    public String getBirthDateString() {
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = outputFormat.format(getBirthDate());
        return formattedDate;
    }

    public String getIdString() {
        return String.valueOf(this.id);
    }
}
