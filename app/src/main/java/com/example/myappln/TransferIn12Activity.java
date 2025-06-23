package com.example.myappln;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TransferIn12Activity extends AppCompatActivity {
    private Customer sender, receiver;
    private int amount;
    private boolean confirm;
    private TextView text;
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer_in12);
        text = findViewById(R.id.textView46);
        home = findViewById(R.id.imageView17);
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        receiver = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("receiverPhoneNumber"));
        amount = getIntent().getIntExtra("amount", 0);
        confirm = getIntent().getBooleanExtra("confirm", false);
        if (confirm) {
            text.setTextColor(Color.parseColor("#00FF0A"));
            text.setText("The amount of " + amount + " is deducted from your account and will be deposited into " + receiver.getFirstName() + " " + receiver.getLastName() + "'s account at 12 o'clock");
            sender.getCard().decreaseBalance(amount + 200);
            Thread transferThread = new Thread(new TransferThread(sender, receiver, amount));
            transferThread.start();
        } else {
            text.setTextColor(Color.parseColor("#FF0000"));
            text.setText("Your account balance is insufficient");

        }
        home.setOnClickListener(view -> {
            Intent intent = new Intent(TransferIn12Activity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", sender.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }
}