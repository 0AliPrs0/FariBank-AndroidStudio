package com.example.myappln;

import java.time.Instant;

import ir.ac.kntu.util.Calendar;

public class Loan {
    private String name;
    private int amount;
    private int numOfInstallments;
    private int numOfInstallmentsPaid;
    private Instant startLoan = null;
    private Instant lastPayment = null;
    private Instant nextPayment = null;
    private LoanStatus status;

    public Loan(String name, int amount, int numOfInstallments) {
        this.name = name;
        this.amount = amount;
        this.numOfInstallments = numOfInstallments;
        numOfInstallmentsPaid = 0;
        status = LoanStatus.PENDING;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumOfInstallments() {
        return numOfInstallments;
    }

    public void setNumOfInstallments(int numOfInstallments) {
        this.numOfInstallments = numOfInstallments;
    }

    public int getNumOfInstallmentsPaid() {
        return numOfInstallmentsPaid;
    }

    public void setNumOfInstallmentsPaid(int numOfInstallmentsPaid) {
        this.numOfInstallmentsPaid = numOfInstallmentsPaid;
    }

    public Instant getStartLoan() {
        return startLoan;
    }

    public void setStartLoan(Instant startLoan) {
        this.startLoan = startLoan;
    }

    public Instant getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(Instant lastPayment) {
        this.lastPayment = lastPayment;
    }

    public Instant getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(Instant nextPayment) {
        this.nextPayment = nextPayment;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public void payTheInstallment() {
        numOfInstallmentsPaid++;
        lastPayment = Calendar.now();
        nextPayment = Calendar.nextMonth(nextPayment);
    }

    public int amountOfInstallment() {
        return (amount + (amount * 10 / 100)) / numOfInstallments;
    }
}
