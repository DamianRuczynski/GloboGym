package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.gym_member.Member;
import com.example.globogym.training.Training;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.globogym.MainApplication.trainingsWithMembers;
import static com.example.globogym.actions.editCoachForm.listOfTrainings;

public class UserStatsController implements Initializable {
    @FXML
    Label userTrainingsCountField, allTrainingsCountField, attendanceField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double memberTrainingCount = getAllUserTrainings();
        double allTrainingsCount = listOfTrainings.size();
        double attendance = (memberTrainingCount / allTrainingsCount) * 100;
        userTrainingsCountField.setText(String.valueOf(memberTrainingCount));
        allTrainingsCountField.setText(String.valueOf(allTrainingsCount));
        String formattedAttendance = new DecimalFormat("#.##").format(attendance);
        attendanceField.setText(formattedAttendance + "%");
    }

    private int getAllUserTrainings() {
        ArrayList<Training> filteredTrainings = new ArrayList<>();
        for (Training training : listOfTrainings) {
            Member member = AllMembersList.membersList.get(((Member) LoginController.loggedUser).getId());
            boolean isAssigned = trainingsWithMembers.get(training.getId()).contains(member);
            if (isAssigned) {
                filteredTrainings.add(training);
            }
        }

        return filteredTrainings.size();
    }
}
