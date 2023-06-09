package core;

public class Account {
    int balance;
    boolean active;
    public int getBalance() {
        return this.balance;
    }

    public void makeActive() {
        this.active = true;
    }
}
