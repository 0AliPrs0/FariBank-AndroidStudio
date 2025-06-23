package com.example.myappln;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ShowInformationActivity extends AppCompatActivity {
    private Customer customer;
    private TextView firstName, lastName, cardNumber, phoneNumber, balance, accountPassword, cardPassword, simCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_information);
        extractAll();
        setAll();
    }

    private void extractAll() {
        firstName = findViewById(R.id.textView53);
        lastName = findViewById(R.id.textView54);
        cardNumber = findViewById(R.id.textView55);
        phoneNumber = findViewById(R.id.textView56);
        balance = findViewById(R.id.textView57);
        accountPassword = findViewById(R.id.textView58);
        cardPassword = findViewById(R.id.textView59);
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        simCharge = findViewById(R.id.simCharge);
    }

    private void setAll() {
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        cardNumber.setText(customer.getCard().getCardNumber());
        phoneNumber.setText(customer.getSimCard().getPhoneNumber());
        balance.setText(Integer.toString(customer.getCard().getBalance()));
        accountPassword.setText(customer.getPassword());
        cardPassword.setText(customer.getCard().getPassword());
        simCharge.setText(Integer.toString(customer.getSimCard().getCharge()));
    }
}