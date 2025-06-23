package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ShowContactActivity extends AppCompatActivity {
    private TextView savedFirstName, savedLastName, customerFirstName, customerLastName, customerPhoneNumber;
    private Button change, delete;
    private Customer customer, savedCustomer;
    private String savedFName, savedLName;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_contact);
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("customerPhoneNumber"));
        savedCustomer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("savedCustomer"));
        savedFName = getIntent().getStringExtra("savedFirstName");
        savedLName = getIntent().getStringExtra("savedLastName");
        position = getIntent().getIntExtra("position", -1);
        extractAll();
        savedFirstName.setText(savedFName);
        savedLastName.setText(savedLName);
        customerFirstName.setText(savedCustomer.getFirstName());
        customerLastName.setText(savedCustomer.getLastName());
        customerPhoneNumber.setText(savedCustomer.getSimCard().getPhoneNumber());
        delete.setOnClickListener(view -> {
            AlertDialog alertDialog = createDialog(savedCustomer);
            alertDialog.show();
        });
        change.setOnClickListener(view -> {
            Intent intent = new Intent(ShowContactActivity.this, ChangeContactActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    private void extractAll() {
        savedFirstName = findViewById(R.id.textView23);
        savedLastName = findViewById(R.id.textView24);
        customerFirstName = findViewById(R.id.textView25);
        customerLastName = findViewById(R.id.textView26);
        customerPhoneNumber = findViewById(R.id.textView27);
        change = findViewById(R.id.changeContact);
        delete = findViewById(R.id.deleteContact);
    }

    private AlertDialog createDialog(Customer customer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure of removing \"" + savedFName + " " + savedLName + "\" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                customer.getContacts().remove(position);
                Intent intent = new Intent(ShowContactActivity.this, ContactActivity.class);
                intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
                intent.putExtra("deletedPosition", position);
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
