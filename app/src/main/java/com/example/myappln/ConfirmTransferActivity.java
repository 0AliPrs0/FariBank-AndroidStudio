package com.example.myappln;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.util.Collections;

import ir.ac.kntu.util.Calendar;

public class ConfirmTransferActivity extends AppCompatActivity {
    private Customer senderCustomer;
    private Customer receiverCustomer;
    private int transferAmount, fee;
    private boolean confirmTransfer;
    private TextView sender, receiver, amount, time, trackingNumber, confirm, error, trackingNumberView;
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_transfer);
        senderCustomer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        receiverCustomer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("receiverPhoneNumber"));
        transferAmount = getIntent().getIntExtra("amount", 0);
        fee = getIntent().getIntExtra("fee", 0);
        confirmTransfer = getIntent().getBooleanExtra("confirm", false);
        extractAll();
        sender.setText(senderCustomer.getFirstName() + " " + senderCustomer.getLastName());
        receiver.setText(receiverCustomer.getFirstName() + " " + receiverCustomer.getLastName());
        amount.setText(Integer.toString(transferAmount));
        Instant timeTransaction = Calendar.now();
        time.setText(timeTransaction.toString());
        if (confirmTransfer) {
            confirm.setTextColor(Color.parseColor("#00FF0A"));
            confirm.setText("Successful operation");
            senderCustomer.getCard().decreaseBalance(fee);
            Transfer transfer = Transfer.doTransfer(senderCustomer, receiverCustomer, transferAmount, timeTransaction);
            trackingNumber.setText(Integer.toString(transfer.getTrackingNumber()));
        } else {
            confirm.setTextColor(Color.parseColor("#FF0000"));
            confirm.setText("Operation failed");
            error.setTextColor(Color.parseColor("#FF0000"));
            error.setText("Insufficient card balance");
            trackingNumberView.setAlpha(0);
        }
        home.setOnClickListener(view -> {
            Intent intent = new Intent(ConfirmTransferActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", senderCustomer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }

    private void extractAll() {
        trackingNumberView = findViewById(R.id.textView39);
        sender = findViewById(R.id.textView40);
        receiver = findViewById(R.id.textView41);
        amount = findViewById(R.id.textView42);
        time = findViewById(R.id.textView43);
        trackingNumber = findViewById(R.id.textView44);
        confirm = findViewById(R.id.textView38);
        error = findViewById(R.id.textView45);
        home = findViewById(R.id.imageView15);
    }
}