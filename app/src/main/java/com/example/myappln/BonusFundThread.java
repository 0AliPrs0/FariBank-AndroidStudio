package com.example.myappln;

import ir.ac.kntu.util.Calendar;

public class BonusFundThread implements Runnable {
    private BonusFund fund;

    public BonusFundThread(BonusFund fund) {
        this.fund = fund;
    }

    public BonusFund getFund() {
        return fund;
    }

    public void setFund(BonusFund fund) {
        this.fund = fund;
    }

    @Override
    public void run() {
        for (int i = 0; i < fund.getPeriod(); i++) {
            if (Calendar.distance(fund.getStarTime(), Calendar.now()) > (i + 1) * 30) {
                fund.increaseBalance(fund.getBalance() * 3 / 100);
            } else {
                i--;
            }
        }
    }
}
