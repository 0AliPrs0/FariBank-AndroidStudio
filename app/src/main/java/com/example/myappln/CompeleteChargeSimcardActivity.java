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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import ir.ac.kntu.util.Calendar;

public class CompeleteChargeSimcardActivity extends AppCompatActivity {
    private Customer sender, receiver;
    private EditText amount;
    private TextView error;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compelete_charge_simcard);
        extractAll();
        confirm.setOnClickListener(view -> {
            if (amount.getText().toString().isEmpty()) {
                error.setText("Please enter the amount");
                return;
            }
            if (sender.getCard().getBalance() < Integer.parseInt(amount.getText().toString()) + (Integer.parseInt(amount.getText().toString())) * 9 / 100) {
                error.setText("Your card balance is insufficient");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createAlertDialog();
            alertDialog.show();
        });
    }

    private void extractAll() {
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        receiver = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("receiverPhoneNumber"));
        amount = findViewById(R.id.editTextPhone7);
        error = findViewById(R.id.textView112);
        confirm = findViewById(R.id.button13);
    }

    private AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about charging the SIM card " + receiver.getFirstName() + " " + receiver.getLastName() + " to the amount of " + amount.getText().toString() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ChargeSimCard.doCharge(sender, receiver, Integer.parseInt(amount.getText().toString()));

                Intent intent = new Intent(CompeleteChargeSimcardActivity.this, DashboardActivity.class);
                intent.putExtra("phoneNumber", sender.getSimCard().getPhoneNumber());
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