package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddToContactActivity extends AppCompatActivity {
    private EditText firstName, lastName, phoneNumber;
    private Button submit;
    private TextView error;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_contact);
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        firstName = findViewById(R.id.savedFirstName);
        lastName = findViewById(R.id.savedLastName);
        phoneNumber = findViewById(R.id.contactPhoneNumber);
        submit = findViewById(R.id.button);
        error = findViewById(R.id.errorEmpty);
        submit.setOnClickListener(view -> {
            if (firstName.getText().toString().isEmpty() && lastName.getText().toString().isEmpty()) {
                error.setText("Please enter first or last name");
                return;
            }
            if (phoneNumber.getText().toString().isEmpty()) {
                error.setText("Please enter phone number");
                return;
            }
            if (phoneNumber.getText().toString().equals(customer.getSimCard().getPhoneNumber())) {
                error.setText("This is yourself!!");
                return;
            }
            if (customer.haveThisContact(phoneNumber.getText().toString())) {
                error.setText("This number is in your contacts");
                return;
            }
            Customer wantToSave = MainActivity.getMainBank().findCustomerWithPhoneNumber(phoneNumber.getText().toString());
            if (wantToSave == null) {
                error.setText("There is no user with this phone number in the bank");
                return;
            }
            customer.getContacts().add(new Contact(firstName.getText().toString(), lastName.getText().toString(), wantToSave));
            Intent intent = new Intent(AddToContactActivity.this, ContactActivity.class);
            intent.putExtra("phoneNumber", getIntent().getStringExtra("phoneNumber"));
            startActivity(intent);
        });
    }
}