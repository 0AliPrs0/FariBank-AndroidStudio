package com.example.myappln;

import ir.ac.kntu.util.Calendar;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fund {
    private String name;
    private int balance;

    public Fund(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void increaseBalance(int amount) {
        balance += amount;
    }

    public void decreaseBalance(int amount) {
        balance -= amount;
    }
}
