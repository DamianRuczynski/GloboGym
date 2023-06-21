package com.example.globogym.gym_member;

import com.example.globogym.manager.Manager;
import core.Account;
import core.ActionLogger;
import core.Role;
import com.example.globogym.training.Training;
import core.User;

public class Member extends User{
    Account account;
    Manager manager;

    public Member(int id, String username, String password, Role role, String name, String surname, String birthdate, String managerId, String accountId) {
        super(id, username, password, role, name, surname, birthdate);
            this.manager = Manager.getManagerById(Integer.parseInt(managerId));
            this.account = Account.haveAccountWithId(Integer.parseInt(accountId)) ? Account.getAccount(Integer.parseInt(accountId)) : new Account(0);
    }


    public void signUpForTraining(Training training) {
        training.setUser(this.id);
    }

    public void signOutFromTraining(Training training) {
        if (training.haveUser(this.id)) {
            training.deleteUser(this.id);
        } else {
            System.out.println("You are not assigned to this training.");
        }
    }

    public void showSchedule() {
        //Load new calendar window with trainings assigned to the user (the best will be array list<Training> in calendar controller)
    }

    private int getAccountBalance() {
        return account.getBalance();
    }

    public void payCard() {
        ActionLogger.setLog("the card is being paid...");
        this.account.makeActive();
        ActionLogger.setLog("user: "+ getName() + " has paid his account");
    }

    public void edit() {
        UserFormController.openEdit(this.id);
        //open edit user form, read all data that user have and write into proper text field in form, give user possibility to overwrite his data.
    }

    public Training showStats(Training training, int id) {
        return training.showStats(id); /// method will display duration od training and name of the exercises he made
    }

    public int getManagerId() {
        return (this.manager.getId() > 0 && this.manager.getId() < Manager.managersList.size()) ? this.manager.getId() : 0 ;
    }


    public int getAccount() {
        return this.account.getBalance();
    }

    public Manager getManager() {
        return this.manager;
    }
}
