package com.example.globogym.core;

import com.example.globogym.staff.Staff;
import com.example.globogym.training.Training;
import core.ActionLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.globogym.actions.editCoachForm.listOfTrainings;
import static com.example.globogym.gym_member.UserFormController.generateMaxId;

public class TrainingFormController {

    private static String FILE_URL = "src/main/data/trainings.txt";

    public static void saveTraining(String name, int roomNumber, Staff coach, String trainingDate) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_URL, true));
            String trainingDTO = generateMaxId("trainings") + "," + name + "," + roomNumber + "," + trainingDate + "," + coach.getId();
            pw.append("\n" + trainingDTO);
            ActionLogger.setLog("training added: " + name);
            System.out.println("training added");
            pw.close();
        } catch (IOException e) {
            ActionLogger.setLog("cannot create training");
        }
    }

    public static void saveTrainings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_URL))) {
            for (Training t : listOfTrainings) {
                String trainingDTO = t.getId() + "," + t.getName() + "," + t.getRoomNumber() + "," + t.getTrainingDateString() + "," + t.getCoach().getId();
                System.out.println(trainingDTO);
                writer.write(trainingDTO);
                writer.newLine();
            }
        } catch (IOException e) {
            ActionLogger.setLog("error occurdd while saving users");
        }
    }
}
