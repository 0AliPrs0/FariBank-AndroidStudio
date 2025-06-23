package com.example.myappln;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import ir.ac.kntu.util.Calendar;

public class TransferThread implements Runnable {
    private Customer sender;
    private Customer receiver;
    private int amount;

    public TransferThread(Customer sender, Customer receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        Instant time = Calendar.now();
        try {
            Thread.sleep(14400);
        } catch (InterruptedException e) {
            System.out.println("Signup thread error");
        }
        receiver.getCard().increaseBalance(amount);
        Instant realTime = Calendar.trimTime(time);
        Transfer transfer = new Transfer(realTime, MainActivity.getMainBank().createTrackingNumber(), amount, sender, receiver);
        List<Transaction> senderTransaction = MainActivity.getMainBank().getTransactions().getOrDefault(sender, new ArrayList<>());
        List<Transaction> receiverTransaction = MainActivity.getMainBank().getTransactions().getOrDefault(receiver, new ArrayList<>());
        senderTransaction.add(transfer);
        receiverTransaction.add(transfer);
        if (!sender.getRecentPeople().contains(receiver)) {
            sender.getRecentPeople().add(receiver);
        }
    }
}
