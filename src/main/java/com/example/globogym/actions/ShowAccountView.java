package com.example.globogym.actions;

import com.example.globogym.LoginController;
import com.example.globogym.gym_member.Member;
import core.ActionLogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAccountView implements Initializable {
    @FXML
    Label nameField, accountBalanceField;
    @FXML
    Button payAccount;

    private Member member = (Member) (LoginController.loggedUser);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameField.setText(member.getName() + " your account balance:");
        accountBalanceField.setText(String.valueOf(member.getAccount()));
        ActionLogger.setLog("Initialize show balance view");
    }

    public void setPayAccount(){
        ActionLogger.setLog( "execute: payAccount ");
        Actions.callMethod("payAccount");
        accountBalanceField.setText(String.valueOf(member.getAccount()));
    }
}
