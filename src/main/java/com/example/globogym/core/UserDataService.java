package com.example.globogym.core;

import com.example.globogym.manager.Manager;
import core.Account;
import core.ActionLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserDataService {

    public static void saveManagers(){
        System.out.println("savaeeeeee");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data/managers.txt"))) {
            for (Manager m : Manager.managersList) {
                String line = m.getId() + "," +
                        m.getUsername() + "," +
                        m.getPassword() + "," +
                        m.getName() + "," +
                        m.getSurname() + "," +
                        m.getBirthDateString() + "," +
                        m.getSalary()+","+
                        m.getManagementStyle() + "," +
                        m.greetings();

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            ActionLogger.setLog("error occurdd while saving users");
        }
    }

    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            BufferedReader input = new BufferedReader(new FileReader("src/main/data/accounts.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] accountData = line.split(",");
                int accountId = Integer.parseInt(accountData[0]);
                int balance = Integer.parseInt(accountData[1]);
                Account account = new Account(accountId, balance);
                accounts.add(account);
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } catch (IOException e) {
            System.out.println("Error: Failed to read file");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid account data");
        }

        return accounts;
    }

    public static void updateAccount(int id) {
        ArrayList<Account> accountsList = getAccounts();
        Optional<Account> optionalAccount = accountsList.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
        optionalAccount.ifPresent(account -> account.makeActive());
        saveAccounts(accountsList);
    }

    private static void saveAccounts(ArrayList<Account> accountsList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data/accounts.txt"));
            for (Account account : accountsList) {
                writer.write(account.getId() + "," + account.getBalance());
                writer.newLine();
            }
            writer.close();
            ActionLogger.setLog("Accounts saved successfully.");
        } catch (IOException e) {
            ActionLogger.setLog("An error occurred while saving the accounts.");
        }
    }


}
