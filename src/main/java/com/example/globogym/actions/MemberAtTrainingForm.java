package com.example.globogym.actions;

import com.example.globogym.gym_member.Member;
import com.example.globogym.training.Training;
import core.ActionLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.globogym.MainApplication.rooms;
import static com.example.globogym.MainApplication.trainingsWithMembers;
import static com.example.globogym.actions.editCoachForm.listOfTrainings;
import static com.example.globogym.core.TrainingFormController.overWriteMembersList;

public class MemberAtTrainingForm implements Initializable {
    @FXML
    ListView<Training> trainingListView;
    @FXML
    ListView<Member> memberListView;
    @FXML
    Label message;

    private void populateFields() {
        for (Member member : AllMembersList.membersList) {
            memberListView.getItems().add(member);
        }
        memberListView.setCellFactory(p -> new ListCell<Member>() {
            @Override
            protected void updateItem(Member item, boolean empty) {
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

    public void assignMember(){
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        Member member = memberListView.getSelectionModel().getSelectedItem();
        if(rooms.get(training.getRoomNumber()) > trainingsWithMembers.get(training.getId()).size()){
            ArrayList<Member> members = trainingsWithMembers.get(training.getId());
            members.add(member);
            trainingsWithMembers.put(training.getId(), members);
            message.setText("MEMBER ADDED");
        }else{
            ActionLogger.setLog("Room is full user not added" + member.getFullName());
            message.setText("THE ROOM IS FULL");
        }

        overWriteMembersList();
    }

    public void removeMember() {
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        Member member = memberListView.getSelectionModel().getSelectedItem();

        if (training != null && member != null) {
            int trainingId = training.getId();
            ArrayList<Member> members = trainingsWithMembers.get(trainingId);

            if (members != null) {
                if (members.removeIf(m -> m.getId() == member.getId())) {
                    trainingsWithMembers.put(trainingId, members);
                    message.setText("Member with ID " + member.getId() + " has been successfully removed from the training.");
                    overWriteMembersList();
                } else {
                    message.setText("Member with ID " + member.getId() + " does not exist in the selected training.");
                }
            } else {
                message.setText("No members are assigned to the selected training.");
            }
        } else {
            message.setText("Please select a training and a member.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String fileName = Arrays.stream(url.getFile().split("/")).reduce((first, second) -> second).orElse(null);
//        this.isDelete = fileName.equals("delete-training-view.fxml");
        populateFields();
    }
}
