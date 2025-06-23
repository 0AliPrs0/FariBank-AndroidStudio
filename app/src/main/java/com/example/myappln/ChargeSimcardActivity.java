package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChargeSimcardActivity extends AppCompatActivity {
    private Customer sender, receiver;
    private Button continueButton, contact, recentPeople;
    private EditText receiverPhoneNumber;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_charge_simcard);
        extractAll();
        continueButton.setOnClickListener(view -> {
            if (receiverPhoneNumber.getText().toString().isEmpty()) {
                error.setText("Please enter phone number");
                return;
            }
            receiver = MainActivity.getMainBank().findCustomerWithPhoneNumber(receiverPhoneNumber.getText().toString());
            if (receiver == null) {
                error.setText("No one was found with this phone number");
                return;
            }
            error.setText("");
            Intent intent = new Intent(ChargeSimcardActivity.this, CompeleteChargeSimcardActivity.class);
            intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
            intent.putExtra("receiverPhoneNumber", receiver.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        contact.setOnClickListener(view -> {
            if (sender.getContacts().isEmpty()) {
                error.setText("You dont have any contact");
                return;
            }
            Intent intent = new Intent(ChargeSimcardActivity.this, ShowContactForChargeSimCardActivity.class);
            intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        recentPeople.setOnClickListener(view -> {
            if (sender.getRecentPeople().isEmpty()) {
                error.setText("You dont have any recent people");
                return;
            }
            Intent intent = new Intent(ChargeSimcardActivity.this, ShowRecentPeopleForChargeSimcardActivity.class);
            intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }

    private void extractAll() {
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        continueButton = findViewById(R.id.button10);
        contact = findViewById(R.id.button11);
        recentPeople = findViewById(R.id.button12);
        receiverPhoneNumber = findViewById(R.id.editTextPhone6);
        error = findViewById(R.id.textView110);
    }
}