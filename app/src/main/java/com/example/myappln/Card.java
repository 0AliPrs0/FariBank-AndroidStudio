package com.example.myappln;

import ir.ac.kntu.util.Calendar;

import java.time.Instant;
import java.util.*;

public class Card {
    private String password;
    private String cardNumber;
    private int balance = 0;


    public Card() {
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public void toppingUp(Customer sender, int amount) {
        this.increaseBalance(amount);
        ArrayList<Transaction> transactions = MainActivity.getMainBank().getTransactions().getOrDefault(sender, new ArrayList<>());
        ToppingUp toppingUp = new ToppingUp(Calendar.now(), MainActivity.getMainBank().createTrackingNumber(), amount);
        transactions.add(toppingUp);
        Collections.sort(transactions);
        MainActivity.getMainBank().getTransactions().put(sender, transactions);

    }
}
