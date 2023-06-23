package com.example.globogym.core;

import com.example.globogym.gym_member.Member;
import com.example.globogym.staff.Staff;
import com.example.globogym.training.Training;
import core.ActionLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.example.globogym.MainApplication.trainingsWithMembers;
import static com.example.globogym.actions.editCoachForm.listOfTrainings;
import static com.example.globogym.core.Helpers.updateMembersList;
import static com.example.globogym.gym_member.UserFormController.generateMaxId;

public class TrainingFormController {

    private static String FILE_URL = "src/main/data/trainings.txt";

    public static void saveTraining(String name, int roomNumber, Staff coach, String trainingDate) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_URL, true));
            int id = generateMaxId("trainings");
            String trainingDTO = id + "," + name + "," + roomNumber + "," + trainingDate + "," + coach.getId();
            updateMembersList(id, new ArrayList<Member>());
            pw.append("\n" + trainingDTO);
            ActionLogger.setLog("training added: " + name);
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create training");
        }
    }

    public static void saveTrainings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_URL))) {
            for (Training t : listOfTrainings) {
                String prefix = t.getId() == 0 ? "":"\n";
                String trainingDTO = prefix + t.getId() + "," + t.getName() + "," + t.getRoomNumber() + "," + t.getTrainingDateString() + "," + t.getCoach().getId();
                updateMembersList(t.getId(), t.getMembers());
                writer.write(trainingDTO);
            }
            overWriteMembersList();
        } catch (IOException e) {
            ActionLogger.setLog("error occurdd while saving users");
        }
    }

    public static void overWriteMembersList() {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("src/main/data/trainingsWithUsers.txt"));
            for (Integer trainingId : trainingsWithMembers.keySet()) {
                ArrayList<Member> members = trainingsWithMembers.get(trainingId);
                output.write(trainingId + ":");
                if (members.isEmpty()) {
                    output.newLine();
                    continue;
                }
                for (int i = 0; i < members.size(); i++) {
                    output.write(members.get(i).getIdString());
                    if (i < members.size() - 1) {
                        output.write(",");
                    }
                }
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            ActionLogger.setLog("An error occurred while writing the training database");
            System.out.println(e.getMessage());
        }finally {
            ActionLogger.setLog("Succesfully saved users to training");
        }
    }



}
