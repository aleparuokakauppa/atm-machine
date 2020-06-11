package com.company;

import java.util.function.DoubleToIntFunction;

public class Main {
    UserInput userInput = new UserInput();
    AccountRepo accountRepo = new AccountRepo("accounts.dat");
    public static void main(String[] args) {
        Main main = new Main();
        main.startMachine();
    }

    public void startMachine() {

        while (true) {
            System.out.print("\nCreate ID. If you already have an ID, press 0 to login. ");
            System.out.print("\n");
            String id = userInput.readString("ID (numbers only)");
            if (id.charAt(0) == '0') break;
            String pin = userInput.readString("pin (numbers only)");
            long amount = userInput.readLong("Set cash amount");
            if (id.matches("[0-9]+")&& id.length() > 2) {
                if (pin.matches("[0-9]+")&& pin.length() > 2) {
                    Account account = new Account(id, pin, amount);
                    accountRepo.addAccount(account);
                    mainMenu(account);
                }
            }
            else {
                System.out.println("ID and pin should be numbers only.");
            }
        }
        for (Account account : accountRepo.getAccounts());
        accountRepo.saveAccounts();
        login();
    }

    public void login() {
        System.out.print("Login to existing ID." + "\n");
        String name = userInput.readString("ID");
        if (accountRepo.doesAccountExist(name)) {
           String password = userInput.readString("pin");
           if ( accountRepo.doesPasswordMatch(password)) {
               mainMenu(accountRepo.getCurrentAccount());
           }
           else System.out.print("pin incorrect");
        }
        else System.out.print("ID doesn't exist. \n");
        startMachine();
    }

    public void mainMenu(Account account) {
        while (true) {
            System.out.print("\nMain menu. Press 1 if you want to initiate an action and 0 if you don't. \n \n");
            String selection = userInput.readString("Your options are: Withdraw (W), Deposit (D), check Saldo (S) or logout (L)");
            if (selection.charAt(0) == 'w') {
                ottoWithdraw(account);
            }
            if (selection.charAt(0) == 'd') {
                ottoDeposit(account);
            }
            if (selection.charAt(0) == 's') {
                System.out.print("\n");
                saldo(account);
            }
            if (selection.charAt(0) == 'l') {
                break;
            }
        }
    }

    public void ottoWithdraw(Account account) {
        long withdraw = userInput.readLong("Withdraw");
        System.out.println("You withdrew : " + withdraw);
        account.reduceMoney(withdraw);
    }

    public void ottoDeposit(Account account) {
        long deposit = userInput.readLong("Deposit");
        System.out.println("You deposited : " + deposit);
        account.depositMoney(deposit);
    }

    public void saldo(Account account) {
        account.printBalance();
    }

}