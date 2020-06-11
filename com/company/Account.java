package com.company;

import java.io.Serializable;

public class Account implements Serializable {
    private String name;
    private String password;
    public long amount;

    public Account(String name, String password, long amount) {
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account : " + name + " " + password + amount;
    }

    public void reduceMoney(long reduce) {
        amount -= reduce;
        System.out.println("Balance left : " + amount);
    }

    public void printBalance() {
        System.out.println("Your balance is : " + amount);
    }

    public void depositMoney(long deposit) {
        amount += deposit;
        System.out.println("Balance left :" + amount);
    }
}