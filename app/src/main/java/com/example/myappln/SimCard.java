package com.example.myappln;

import ir.ac.kntu.util.Calendar;

import java.util.ArrayList;
import java.util.Collections;

public class SimCard {
    private String phoneNumber;
    private int charge = 0;

    public SimCard(String phoneNumber, int charge) {
        this.phoneNumber = phoneNumber;
        this.charge = charge;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public void increaseCharge(int amount) {
        charge += amount;
    }
}
