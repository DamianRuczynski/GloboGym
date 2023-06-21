package core;

import java.util.ArrayList;

public class Account {
    public static ArrayList<Account> accountsList = new ArrayList<Account>();
    int balance;
    boolean active;

    public Account(int balance){
        this.balance = balance;
        this.active = true;
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
}
