package com.example.myappln;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ShowTransactionActivity extends AppCompatActivity {
    private TextView transactionType, amountView, amount, timeView, time, trackingView, tracking, senderView, sender, receiverView, receiverText;
    private Transaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_transaction);
        int trackingNumber = getIntent().getIntExtra("trackingNumber", 0);
        Transaction transaction = MainActivity.getMainBank().findWithTrackingNumber(trackingNumber);
        if (transaction == null) {
            Toast.makeText(this, "There is a problem", Toast.LENGTH_SHORT).show();
            return;
        }
        extractAll();
        transactionType.setText("TOPPING UP");
        amountView.setText("Amount:");
        timeView.setText("Time:");
        trackingView.setText("Tracking number:");
        amount.setText(Integer.toString(transaction.getAmount()));
        time.setText(transaction.getTime().toString());
        tracking.setText(Integer.toString(transaction.getTrackingNumber()));
        if (transaction instanceof Transfer) {
            Transfer transfer = (Transfer) transaction;
            transactionType.setText("TRANSFER");
            senderView.setText("Sender:");
            receiverView.setText("Receiver:");
            sender.setText(transfer.getSender().getFirstName() + " " + transfer.getSender().getLastName());
            receiverText.setText(transfer.getReceiver().getFirstName() + " " + transfer.getReceiver().getLastName());
        } else if (transaction instanceof ChargeSimCard) {
            ChargeSimCard charge = (ChargeSimCard) transaction;
            transactionType.setText("CHARGE SIMCARD");
            senderView.setText("Sender:");
            receiverView.setText("Receiver:");
            sender.setText(charge.getSender().getFirstName() + " " + charge.getSender().getLastName());
            sender.setText(charge.getSender().getFirstName() + " " + charge.getSender().getLastName());
            Customer receiver = MainActivity.getMainBank().findCustomerWithPhoneNumber(charge.getReceiver().getPhoneNumber());
            receiverText.setText(receiver.getFirstName() + " " + receiver.getLastName());
        }
    }

    private void extractAll() {
        transactionType = findViewById(R.id.textView14);
        amountView = findViewById(R.id.textView3);
        amount = findViewById(R.id.textView9);
        timeView = findViewById(R.id.textView4);
        time = findViewById(R.id.textView10);
        trackingView = findViewById(R.id.textView6);
        tracking = findViewById(R.id.textView11);
        senderView = findViewById(R.id.textView7);
        sender = findViewById(R.id.textView12);
        receiverView = findViewById(R.id.textView8);
        receiverText = findViewById(R.id.textView13);
    }
}