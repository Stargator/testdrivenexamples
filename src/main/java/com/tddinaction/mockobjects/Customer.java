package com.tddinaction.mockobjects;

public class Customer {
    private float balance;

    public Customer(float initialBalance) {
        this.balance = initialBalance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
