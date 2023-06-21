package com.example.globogym.training;
import com.example.globogym.actions.TrainingController;
import com.example.globogym.gym_member.Member;
import com.example.globogym.staff.Staff;

import java.util.ArrayList;
import java.util.Date;

public class Training {
    int id;
    String name;
    int roomId;
    Date startDate;
    Date endDate;
    Staff coach;
    ArrayList<Member> listOfMembers;

    public Training(int id, String name, int roomId, Date startDate, Date endDate, int coachId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coach = Staff.getCoachById(coachId);
        this.listOfMembers = TrainingController.trainingsWithMembers.get(id);
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

}
