package com.example.globogym.training;
import com.example.globogym.core.Helpers;
import com.example.globogym.core.Identifiable;
import com.example.globogym.gym_member.Member;
import com.example.globogym.staff.Staff;

import java.util.ArrayList;
import java.util.Date;

import static com.example.globogym.MainApplication.trainingsWithMembers;

public class Training implements Identifiable {
    int id;
    String name;
    int roomId;
    Date date;
    Staff coach;
    ArrayList<Member> listOfMembers;

    public Training(int id, String name, int roomId, Date date, int coachId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.date = date;
        this.coach = Staff.getCoachById(coachId);
        this.listOfMembers = trainingsWithMembers.get(id) == null ? new ArrayList<>() : trainingsWithMembers.get(id);
    }

    public Training showStats(int id) {
        return this;
    }

    public void setUser(int id) {
    }

    public boolean haveUser(int id) {
        return false;
    }

    public void deleteUser(int id) {
    }

    public String getName() {
        return this.name;
    }

    public String getDateAndName() {
        return Helpers.parseDataToStringFormat(this.date)+ " - " + this.name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setCoach(Staff coach) {
        this.coach = coach;
    }

    public int getRoomNumber() {
        return this.roomId;
    }

    public String getTrainingDateString() {
        return Helpers.parseDataToStringFormat(this.date);
    }

    public Staff getCoach() {
        return this.coach;
    }

    public ArrayList<Member> getMembers() {
        return this.listOfMembers;
    }

    public ArrayList<Member> getListOfMembers() {
        return this.listOfMembers;
    }
}
