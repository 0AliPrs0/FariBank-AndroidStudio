package com.example.myappln;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ir.ac.kntu.util.Calendar;

public class LoanThread implements Runnable {
    private Customer customer;
    private Loan loan;
    private Instant time;

    public LoanThread(Customer customer, Loan loan) {
        this.customer = customer;
        this.loan = loan;
    }

    @Override
    public void run() {
        List<Loan> loans = MainActivity.getMainBank().getAllLoans().getOrDefault(customer, new ArrayList<Loan>());
        loans.add(loan);
        MainActivity.getMainBank().getAllLoans().put(customer, (ArrayList<Loan>) loans);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Loan thread interupted");
        }
        Instant now = Calendar.now();
        if (customer.canGetLoan()) {
            customer.getCard().increaseBalance(loan.getAmount());
            loan.setStatus(LoanStatus.ACCEPTED);
            loan.setStartLoan(now);
            loan.setNextPayment(Calendar.nextMonth(now));
        } else {
            loan.setStatus(LoanStatus.FAILED);
        }
    }
}