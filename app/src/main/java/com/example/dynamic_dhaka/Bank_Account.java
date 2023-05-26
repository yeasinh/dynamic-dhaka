package com.example.dynamic_dhaka;

public class Bank_Account {
    int account_id;
    double balance;

    public Bank_Account(int account_id) {
        this.account_id = account_id;
        this.balance=00.00;
    }

    public Bank_Account() {

    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
