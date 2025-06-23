package com.example.myappln;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ir.ac.kntu.util.Calendar;

public class ChargeSimCard extends Transaction {
    private Customer sender;
    private SimCard receiver;

    public ChargeSimCard(Instant time, int trackingNumber, int amount, Customer sender, SimCard receiver) {
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

    public SimCard getReceiver() {
        return receiver;
    }

    public void setReceiver(SimCard receiver) {
        this.receiver = receiver;
    }

    public static void doCharge(Customer sender, Customer receiver, int amount) {
        sender.getCard().decreaseBalance(amount + (amount * 9 / 100));
        receiver.getSimCard().increaseCharge(amount);
        ChargeSimCard chargeSimCard = new ChargeSimCard(Calendar.now(), MainActivity.getMainBank().createTrackingNumber(), amount, sender, receiver.getSimCard());
        List<Transaction> senderTransaction = MainActivity.getMainBank().getTransactions().getOrDefault(sender, new ArrayList<Transaction>());
        senderTransaction.add(chargeSimCard);
        Collections.sort(senderTransaction);
        if (sender != receiver) {
            List<Transaction> receiverTransactions = MainActivity.getMainBank().getTransactions().getOrDefault(receiver, new ArrayList<Transaction>());
            receiverTransactions.add(chargeSimCard);
            Collections.sort(receiverTransactions);
        }
        if (!sender.getRecentPeople().contains(receiver) && sender != receiver) {
            sender.getRecentPeople().add(receiver);
        }
    }
}
