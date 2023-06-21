package core;

import com.example.globogym.core.UserDataService;

import java.util.ArrayList;

public class Account {
    public static ArrayList<Account> accountsList = UserDataService.getAccounts();
    int id;
    int balance;
    boolean active;

    public Account(int id, int balance){
        this.id = id;
        this.balance = balance;
        this.active = this.balance > 0;
    }

    public static boolean haveAccountWithId(int accountId) {
        return (accountId < accountsList.size() && accountId > 0);
    }

    public static Account getAccount(int id) {
        return accountsList.get(id);
    }

    public int getBalance() {
        return this.balance;
    }

    public void makeActive() {
        this.active = true;
        this.balance += 100;
    }

    public int getId() {
        return this.id;
    }
}
