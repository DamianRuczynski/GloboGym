package com.example.globogym.actions;

import com.example.globogym.MainApplication;
import com.example.globogym.core.TrainingFormController;
import com.example.globogym.staff.Staff;
import com.example.globogym.training.Training;
import core.ActionLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.globogym.actions.editCoachForm.listOfTrainings;

public class AddTrainingForm implements Initializable {

    @FXML
    ListView nameListField, roomListField,staffListField;
    @FXML
    DatePicker trainingDateField;
    @FXML
    Label message;
    public static String[] trainingNames = new String[]{"abs", "push-ups","running","weightlifting"};
    private void populateFields() {
        for (String name : trainingNames) {
            nameListField.getItems().add(name);
        }

        for(Integer number: MainApplication.rooms.keySet()){
            roomListField.getItems().add(number);
        }

        for (Staff coach : AllMembersList.staffList) {
            staffListField.getItems().add(coach);
        }
        staffListField.setCellFactory(p -> new ListCell<Staff>() {
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

    public void saveForm(){

        if(nameListField.getSelectionModel().getSelectedItem() != null && roomListField.getSelectionModel().getSelectedItem() != null && staffListField.getSelectionModel().getSelectedItem() != null && trainingDateField.getValue() != null){
            String name = (String) nameListField.getSelectionModel().getSelectedItem();
            int roomNumber = (int) roomListField.getSelectionModel().getSelectedItem();
            Staff coach = (Staff) staffListField.getSelectionModel().getSelectedItem();
            String trainingDate = trainingDateField.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if(checkRoomFree(trainingDate, roomNumber)){
                message.setText("Training added");
                TrainingFormController.saveTraining(name, roomNumber, coach, trainingDate);
            }else{
                message.setText("Room is reservated for this date frame");
                ActionLogger.setLog("Cannot assign trainigng to reservated room");
            }
        }else{
            message.setText("Form invalid");
        }

    }

    private boolean checkRoomFree(String trainingDate, int roomNumber) {
        for(Training t : listOfTrainings){
            if(t.getTrainingDateString().equals(trainingDate)) {
                if(t.getRoomNumber() == roomNumber)return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateFields();
    }
}
