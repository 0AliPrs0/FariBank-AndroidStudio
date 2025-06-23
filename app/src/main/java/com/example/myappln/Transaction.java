package com.example.myappln;

import ir.ac.kntu.util.Calendar;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements Comparable<Transaction> {
    private Instant time;
    private int amount;
    private int trackingNumber;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(int trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Transaction(Instant time, int trackingNumber, int amount) {
        this.time = time;
        this.amount = amount;
        this.trackingNumber = trackingNumber;
    }

    @Override
    public int compareTo(Transaction transaction) {
        boolean after = this.time.isAfter(transaction.time);
        if (!after) {
            return 1;
        }
        return -1;
    }
}
