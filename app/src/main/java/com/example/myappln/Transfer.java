package com.example.myappln;

import ir.ac.kntu.util.Calendar;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Transfer extends Transaction {
    private Customer sender;
    private Customer receiver;

    public Transfer(Instant time, int trackingNumber, int amount, Customer sender, Customer receiver) {
        super(time, trackingNumber, amount);
        this.sender = sender;
        this.receiver = receiver;
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

    public static Transfer doTransfer(Customer sender, Customer receiver, int amount, Instant time) {
        sender.getCard().decreaseBalance(amount);
        receiver.getCard().increaseBalance(amount);
        Transfer transfer = new Transfer(time, MainActivity.getMainBank().createTrackingNumber(), amount, sender, receiver);
        List<Transaction> senderTransaction = MainActivity.getMainBank().getTransactions().getOrDefault(sender, new ArrayList<Transaction>());
        List<Transaction> receiverTransaction = MainActivity.getMainBank().getTransactions().getOrDefault(receiver, new ArrayList<Transaction>());
        senderTransaction.add(transfer);
        receiverTransaction.add(transfer);
        MainActivity.getMainBank().getTransactions().put(sender, (ArrayList) senderTransaction);
        MainActivity.getMainBank().getTransactions().put(receiver, (ArrayList) receiverTransaction);
        if (!sender.getRecentPeople().contains(receiver)) {
            sender.getRecentPeople().add(receiver);
        }
        Collections.sort(MainActivity.getMainBank().getTransactions().get(sender));
        Collections.sort(MainActivity.getMainBank().getTransactions().get(receiver));
        return transfer;
    }
}
