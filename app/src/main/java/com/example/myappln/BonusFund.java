package com.example.myappln;

import java.time.Instant;

public class BonusFund extends Fund {
    private int period;
    private Instant starTime;
    private boolean end = false;

    public BonusFund(String name, int balance, int period, Instant starTime) {
        super(name, balance);
        this.period = period;
        this.starTime = starTime;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Instant getStarTime() {
        return starTime;
    }

    public void setStarTime(Instant starTime) {
        this.starTime = starTime;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
