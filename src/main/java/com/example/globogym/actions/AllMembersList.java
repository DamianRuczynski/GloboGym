package com.example.globogym.actions;

import com.example.globogym.core.ListUser;
import com.example.globogym.gym_member.Member;
import com.example.globogym.staff.Staff;
import core.ActionLogger;
import core.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllMembersList implements Initializable {
    @FXML
    ListView membersListView, staffListView;
    public static ArrayList<Member> membersList = ListUser.generateList(Role.MEMBER);
    public static ArrayList<Staff> staffList = ListUser.generateList(Role.STAFF);
    public static ObservableList<Label> members = FXCollections.observableArrayList();
    public static ObservableList<Label> staffObservable = FXCollections.observableArrayList();

    public void generateMembersList() {
        membersListView.setPadding(new Insets(10));
        for (Member member : membersList) {
            Label label = new Label();
            label.setText(member.getFullName());
            members.add(label);
            membersListView.getItems().add(label);
        }
    }

    public void generateStaffList() {
        staffListView.setPadding(new Insets(10));
        for (Staff staff : staffList) {
            Label label = new Label();
            label.setText(staff.getFullName());
            staffObservable.add(label);
            staffListView.getItems().add(label);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActionLogger.setLog("load all members list");
        generateMembersList();
        generateStaffList();
    }
}
