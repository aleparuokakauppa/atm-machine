package com.company;

import java.io.*;
import java.net.PasswordAuthentication;
import java.util.Vector;

public class AccountRepo implements Serializable {
    private Vector<Account> accounts;
    private String path;
    private Account currentAccount;

    public AccountRepo(String path) {
        this.accounts = new Vector<>();
        this.path = path;
        currentAccount = null;
        readAccounts();
    }

    public Vector<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Vector<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public void addAccount(Account account) {
        accounts.addElement(account);
    }

    public void saveAccounts() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            for (Account account : accounts) {
                objectOutputStream.writeObject(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saveAccounts!");
        }
    }

    public void readAccounts() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            Account account = (Account) objectInputStream.readObject();
            while (account != null) {
                accounts.addElement(account);
                account = (Account) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("");
        }
    }

    public boolean doesAccountExist(String name) {
        for (Account account : accounts ) {
            if (account.getName().equals(name)) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    public boolean doesPasswordMatch(String password) {
        return currentAccount.getPassword().equals(password);
    }
}
