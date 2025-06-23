package com.example.myappln;

import ir.ac.kntu.util.Calendar;

import java.time.Instant;

public class ToppingUp extends Transaction {
    public ToppingUp(Instant time, int trackingNumber, int amount) {
        super(time, trackingNumber, amount);
    }

    public static ToppingUp createToppingUp(NeoBank neoBank, int amount) {
        int trackingNumber = MainActivity.getMainBank().createTrackingNumber();
        Instant time = Calendar.now();
        return new ToppingUp(time, trackingNumber, amount);
    }
}
