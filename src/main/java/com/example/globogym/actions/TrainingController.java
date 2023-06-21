package com.example.globogym.actions;

import com.example.globogym.gym_member.Member;
import com.example.globogym.training.Training;
import core.ActionLogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TrainingController {

    public static ArrayList<Training> listOfTrainings;

    public static HashMap<Integer, ArrayList<Member>> trainingsWithMembers;




    public static void initialize(URL url, ResourceBundle resourceBundle) {
//        trainingsWithMembers = generateTrainingsAssigning(); STH WRONG WITH READING FROM THE FILE NEED TO CONTROL IT
//        listOfTrainings = generateTrainingsList();
        System.out.println("zaladowany");
    }



    private ArrayList<Training> generateTrainingsList() {
        ArrayList<Training> trainingsList = new ArrayList<>();

        try {
            BufferedReader input = new BufferedReader(new FileReader("trainings.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] trainingData = line.split(",");
                int trainingId = Integer.parseInt(trainingData[0]);
                String trainingName = trainingData[1];
                int roomId = Integer.parseInt(trainingData[2]);
                Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(trainingData[3]);
                int coachId = Integer.parseInt(trainingData[4]);

                Training training = new Training(trainingId, trainingName, roomId, startDate, coachId);
                trainingsList.add(training);
            }
            input.close();
        } catch (FileNotFoundException e) {
            ActionLogger.setLog("An error occurred while reading the trainings file - file not found");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            ActionLogger.setLog("Somehow the trainings file is empty");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            ActionLogger.setLog("An error occurred while parsing the training dates");
            System.out.println(e.getMessage());
        }
        return trainingsList;
    }

    private HashMap<Integer, ArrayList<Member>> generateTrainingsAssigning() {
        HashMap<Integer, ArrayList<Member>> trainingsAssigning = new HashMap<>();

        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/trainingsWithUsers.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] trainingData = line.split(";");
                int trainingId = Integer.parseInt(trainingData[0]);
                String[] memberIds = trainingData[1].split(",");
                ArrayList<Member> members = new ArrayList<>();
                for (String memberId : memberIds) {
                    int memberIdInt = Integer.parseInt(memberId);
                    members.add(AllMembersList.membersList.get(memberIdInt));
                }
                trainingsAssigning.put(trainingId, members);
            }

            input.close();
        } catch (FileNotFoundException e) {
            ActionLogger.setLog("An error occurred while reading the training database - file not found");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            ActionLogger.setLog("Somehow the training database is empty");
            System.out.println(e.getMessage());
        }

        return trainingsAssigning;
    }

}
