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

public class ToppingUpActivity extends AppCompatActivity {
    private Customer customer;
    private String phoneNumber;
    private EditText amount;
    private Button confirm;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topping_up);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(phoneNumber);
        amount = findViewById(R.id.amount);
        confirm = findViewById(R.id.confirmToppingUp);
        error = findViewById(R.id.error2);
        confirm.setOnClickListener(view -> {
            if (amount.getText().toString().isEmpty()) {
                error.setText("Please enter the amount");
                return;
            }
            AlertDialog alertDialog = createDialog(Integer.parseInt(amount.getText().toString()));
            alertDialog.show();
        });
    }

    AlertDialog createDialog(int amount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you confirm the amount of " + amount + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customer.getCard().toppingUp(customer, amount);
                Intent intent = new Intent(ToppingUpActivity.this, DashboardActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
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