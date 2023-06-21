package com.example.globogym.training;
import com.example.globogym.core.Helpers;
import com.example.globogym.gym_member.Member;
import com.example.globogym.staff.Staff;

import java.util.ArrayList;
import java.util.Date;
public class Training {
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
        this.listOfMembers = new ArrayList<Member>();
    }



//    private ArrayList<Member> getTrainingMembers(int id) {
//        ArrayList<Member> members = new ArrayList<>();
//        try {
//            BufferedReader input = new BufferedReader(new FileReader("src/main/data/trainingsWithUsers.txt"));
//            String line;
//            while ((line = input.readLine()) != null) {
//                String[] trainingData = line.split(";");
//                int trainingId = Integer.parseInt(trainingData[0]);
//                if (trainingId == id) {
//                    String[] memberIds = trainingData[1].split(",");
//                    for (String memberId : memberIds) {
//                        int memberIdInt = Integer.parseInt(memberId);
//                        members.add(AllMembersList.membersList.get(memberIdInt));
//                    }
//                    break;
//                }
//            }
//            input.close();
//        } catch (FileNotFoundException e) {
//            ActionLogger.setLog("An error occurred while reading the training database - file not found");
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            ActionLogger.setLog("Somehow the training database is empty");
//            System.out.println(e.getMessage());
//        }
//        return members;
//    }


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
}
