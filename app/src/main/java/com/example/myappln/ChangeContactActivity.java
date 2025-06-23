package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeContactActivity extends AppCompatActivity {
    private EditText newFName, newLName;
    private Button confirm;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        int position = getIntent().getIntExtra("position", -1);
        Customer customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        setContentView(R.layout.activity_change_contact);
        newFName = findViewById(R.id.newFName);
        newLName = findViewById(R.id.newLname);
        confirm = findViewById(R.id.confirmChange);
        error = findViewById(R.id.error4);

        confirm.setOnClickListener(view -> {
            if (newFName.getText().toString().isEmpty() && newLName.getText().toString().isEmpty()) {
                error.setText("Please enter first or last name");
                return;
            }
            error.setText("");
            Contact contact = customer.getContacts().get(position);
            AlertDialog alertDialog = createDialog(contact);
            alertDialog.show();

        });
    }

    private AlertDialog createDialog(Contact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about the change of \"" + contact.getSavedFirstName() + " " + contact.getSavedLastName() + "\" to \"" + newFName.getText().toString() + " " + newLName.getText().toString() + "\"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Customer customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
                int position = getIntent().getIntExtra("position", -1);
                Contact contact = customer.getContacts().get(position);
                contact.setSavedFirstName(newFName.getText().toString());
                contact.setSavedLastName(newLName.getText().toString());
                Intent intent = new Intent(ChangeContactActivity.this, ContactActivity.class);
                intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        return builder.create();
    }
}