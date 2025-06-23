package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TransferActivity extends AppCompatActivity {
    private Customer sender, receiver;
    private Button continueButton, contact, recent;
    private EditText cardNumber;
    private TextView error, error2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer);
        extractAll();
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        continueButton.setOnClickListener(view -> {
            if (cardNumber.getText().toString().isEmpty()) {
                error.setText("Please enter the card number");
                return;
            }
            receiver = MainActivity.getMainBank().findCustomerWithCardNumber(cardNumber.getText().toString());
            if (receiver == null) {
                error.setText("The card number is wrong");
                return;
            }
            if (receiver.getSimCard().getPhoneNumber().equals(sender.getSimCard().getPhoneNumber())) {
                error.setText("This is yourself!!");
                return;
            }
            error.setText("");
            Intent intent = new Intent(TransferActivity.this, CompleteTransferActivity.class);
            intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
            intent.putExtra("receiverPhoneNumber", receiver.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        contact.setOnClickListener(view -> {
            if (sender.getContacts().isEmpty()) {
                error2.setText("You dont have any contact");
                return;
            }
            Intent intent = new Intent(TransferActivity.this, ShowContactForTransferActivity.class);
            intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        recent.setOnClickListener(view -> {
            if (sender.getRecentPeople().isEmpty()) {
                error2.setText("You dont have any recent people");
                return;
            }
            Intent intent = new Intent(TransferActivity.this, ShowRecentPeopleForTransferActivity.class);
            intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }

    private void extractAll() {
        cardNumber = findViewById(R.id.cardNumber);
        continueButton = findViewById(R.id.button4);
        contact = findViewById(R.id.transferWithContact);
        recent = findViewById(R.id.transferWithRecent);
        error = findViewById(R.id.textView31);
        error2 = findViewById(R.id.textView32);
    }
}