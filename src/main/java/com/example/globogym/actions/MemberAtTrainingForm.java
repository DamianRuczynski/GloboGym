package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.gym_member.Member;
import com.example.globogym.gym_member.MemberAction;
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
    private boolean isForUser;
    private MemberAction memberAction;

    private void populateFields() {

        if (!isForUser) {
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
        }

        if (isForUser) {
            ArrayList<Training> filteredList = switch (this.memberAction) {
                case ENTER -> populateEnteranceTrainings();
                case SIGN, SHOW -> populateAssignedTrainings();
            };
            for (Training training : filteredList) {
                trainingListView.getItems().add(training);
            }
        }else{
            for (Training training : listOfTrainings) {
                trainingListView.getItems().add(training);
            }
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

    private ArrayList<Training> populateEnteranceTrainings() {
        ArrayList<Training> filteredTrainings = new ArrayList<>();
        for (Training training : listOfTrainings) {
            Member member = AllMembersList.membersList.get(((Member) LoginController.loggedUser).getId());
            boolean isAssigned = trainingsWithMembers.get(training.getId()).contains(member);
            if (!isAssigned) {
                filteredTrainings.add(training);
            }
        }
        return filteredTrainings;
    }

    private ArrayList<Training> populateAssignedTrainings() {
        ArrayList<Training> filteredTrainings = new ArrayList<>();
        for (Training training : listOfTrainings) {
            Member member = AllMembersList.membersList.get(((Member) LoginController.loggedUser).getId());
            boolean isAssigned = trainingsWithMembers.get(training.getId()).contains(member);
            if (isAssigned) {
                filteredTrainings.add(training);
            }
        }

        return filteredTrainings;
    }

    public void assignMember() {
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        Member member = memberListView.getSelectionModel().getSelectedItem();
        if (rooms.get(training.getRoomNumber()) > trainingsWithMembers.get(training.getId()).size()) {
            ArrayList<Member> members = trainingsWithMembers.get(training.getId());
            members.add(member);
            trainingsWithMembers.put(training.getId(), members);
            message.setText("MEMBER ADDED");
        } else {
            ActionLogger.setLog("Room is full user not added" + member.getFullName());
            message.setText("THE ROOM IS FULL");
        }

        overWriteMembersList();
    }

    public void assignSingleMemberToTraining() {
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        if (rooms.get(training.getRoomNumber()) > trainingsWithMembers.get(training.getId()).size()) {
            ArrayList<Member> members = trainingsWithMembers.get(training.getId());
            Member member = AllMembersList.membersList.get(((Member) LoginController.loggedUser).getId());
            members.add(member);
            trainingsWithMembers.put(training.getId(), members);
            message.setText("MEMBER ADDED");
        } else {
            ActionLogger.setLog("Room is full user not added" + LoginController.loggedUser.getFullName());
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


    public void deleteMemberFromTraining() {
        System.out.println("deleting member...");
        Training training = trainingListView.getSelectionModel().getSelectedItem();
        Member member = AllMembersList.membersList.get(((Member) LoginController.loggedUser).getId());

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
        String fileName = Arrays.stream(url.getFile().split("/")).reduce((first, second) -> second).orElse(null);
        this.isForUser = fileName.equals("enter-training-view.fxml") || fileName.equals("sign-out-from-training-view.fxml") || fileName.equals("show-trainings-view.fxml");
        if(this.isForUser){
            String action = fileName.split("-")[0];
            System.out.println(action);
            this.memberAction = MemberAction.valueOf(action.toUpperCase());
        }
        populateFields();
    }

}
