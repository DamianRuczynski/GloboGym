package com.example.globogym.actions;

import com.example.globogym.core.TrainingFormController;
import com.example.globogym.gym_member.Member;
import com.example.globogym.staff.Staff;
import com.example.globogym.training.Training;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class editCoachForm implements Initializable {
    @FXML
    ListView<Training> trainingListView;
    @FXML
    ListView<Staff> coachListView;

    public static ArrayList<Training> listOfTrainings = TrainingController.generateTrainingsList();

    public static HashMap<Integer, ArrayList<Member>> trainingsWithMembers = TrainingController.generateTrainingsAssigning();


    public void editCoachOnTraining(){
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        Staff coach = coachListView.getSelectionModel().getSelectedItem();
        for(Training t : listOfTrainings){
            if(t.getId() == training.getId()){
                t.setCoach(coach);
            }
        }
        TrainingFormController.saveTrainings();
    }


    private void populateFields() {
        for (Staff coach : AllMembersList.staffList) {
            coachListView.getItems().add(coach);
        }
        coachListView.setCellFactory(p -> new ListCell<Staff>() {
            @Override
            protected void updateItem(Staff item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getFullName() == null) {
                    setText(null);
                } else {
                    setText(item.getFullName());
                }
            }
        });

        for (Training training : listOfTrainings) {
            trainingListView.getItems().add(training);
        }
        trainingListView.setCellFactory(p -> new ListCell<Training>() {
            @Override
            protected void updateItem(Training item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getDateAndName() == null) {
                    setText(null);
                } else {
                    setText(item.getDateAndName());
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        populateFields();
    }
}
