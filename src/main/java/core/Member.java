package core;

import com.example.globogym.gym_member.UserFormController;

public class Member extends User {
    Account account;
    Manager manager;

    public Member(int id, String username, String password, Role role) {
        super(id, username, password, role);
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
        this.account.makeActive();
    }

    public void edit() {
        UserFormController.openEdit(this.id);
        //open edit user form, read all data that user have and write into proper text field in form, give user possibility to overwrite his data.
    }

    public Training showStats(Training training, int id) {
        return training.showStats(id); /// method will display duration od training and name of the exercises he made
    }
}
