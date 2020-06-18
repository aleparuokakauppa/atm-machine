package com.company;

import java.sql.SQLOutput;
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
            System.out.println("******** Welcome! ********");
            System.out.println("* Login (l)              *");
            System.out.println("* Create new account (c) *");
            System.out.println("* Shut down (s)          *");
            System.out.println("**************************");
            String action = userInput.readString("\nSelect an action");
            if (action.charAt(0) == 'l') login();
            if (action.charAt(0) == 'c') {
                String username = userInput.readString("Username");
                String pin = userInput.readString("4 digit pin");
                if (pin.matches("[0-9]+")&& pin.length() > 3) {
                    long amount = userInput.readLong("How much money would you like to deposit?");
                    Account account = new Account(username, pin, amount);
                    accountRepo.addAccount(account);
                    mainMenu(account);
                }
                else {
                    System.out.println("pin should be a 4 digit number.");
                }
            }
            if (action.charAt(0) == 's') {
                System.out.println("");
                System.out.println("Shutting down.");
                accountRepo.saveAccounts();
                break;
            }
            else {
                System.out.println("Enter l, c or s!");
            }
        }
    }

    public void login() {
        System.out.print("\nLogin to existing ID.\n");
        String name = userInput.readString("ID");
        if (accountRepo.doesAccountExist(name)) {
           String password = userInput.readString("pin");
           if ( accountRepo.doesPasswordMatch(password)) {
               mainMenu(accountRepo.getCurrentAccount());
           }
           else System.out.print("pin incorrect\n");
        }
        else System.out.print("ID doesn't exist. \n");
        startMachine();
    }

    public void mainMenu(Account account) {
        while (true) {
            System.out.println("");
            System.out.println("********* Menu ********");
            System.out.println("* View balance (b)    *");
            System.out.println("* Deposit (d)         *");
            System.out.println("* Withdraw (w)        *");
            System.out.println("* Log out (x)         *");
            System.out.println("***********************");
            System.out.println("");
            String selection = userInput.readString("Select an action");
            if (selection.charAt(0) == 'w') {
                ottoWithdraw(account);
            }
            if (selection.charAt(0) == 'd') {
                ottoDeposit(account);
            }
            if (selection.charAt(0) == 'b') {
                System.out.print("\n");
                saldo(account);
            }
            if (selection.charAt(0) == 'x') {
                break;
            }
        }
    }

    public void ottoWithdraw(Account account) {
        long withdraw = userInput.readLong("Withdraw");
        System.out.println("\nYou withdrew : " + withdraw);
        account.reduceMoney(withdraw);
    }

    public void ottoDeposit(Account account) {
        long deposit = userInput.readLong("Deposit");
        System.out.println("\nYou deposited : " + deposit);
        account.depositMoney(deposit);
    }

    public void saldo(Account account) {
        account.printBalance();
    }

}