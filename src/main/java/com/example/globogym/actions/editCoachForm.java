package com.example.globogym.actions;

import com.example.globogym.core.Helpers;
import com.example.globogym.core.TrainingController;
import com.example.globogym.core.TrainingFormController;
import com.example.globogym.staff.Staff;
import com.example.globogym.training.Training;
import core.ActionLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class editCoachForm implements Initializable {
    @FXML
    ListView<Training> trainingListView;
    @FXML
    ListView<Staff> coachListView;
    @FXML
    Label message;

    boolean isDelete = true;

    public static ArrayList<Training> listOfTrainings = TrainingController.generateTrainingsList();


    public void editCoachOnTraining(){
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        Staff coach = coachListView.getSelectionModel().getSelectedItem();
        for(Training t : listOfTrainings){
            if(t.getId() == training.getId()){
                t.setCoach(coach);
            }
        }
        TrainingFormController.saveTrainings();
        message.setText("TRAINING EDITED");
        ActionLogger.setLog("Training: " + training.getDateAndName() + " edited. new coach is: " + coach.getFullName());
    }

    public void deleteTraining(){
        ActionLogger.setLog("delete training..");
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        listOfTrainings.remove(training.getId());
        Helpers.repairIds(listOfTrainings);
        TrainingFormController.saveTrainings();
        ActionLogger.setLog("Training: " + training.getDateAndName() + " deleted.");
        message.setText("TRAINING DELETED");
        listOfTrainings = TrainingController.generateTrainingsList();
    }


    private void populateFields() {
        if(!this.isDelete){
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
        }

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
        String fileName = Arrays.stream(url.getFile().split("/")).reduce((first, second) -> second).orElse(null);
        this.isDelete = fileName.equals("delete-training-view.fxml");
        populateFields();
    }
}
