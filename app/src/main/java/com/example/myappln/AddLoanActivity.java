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
import java.util.List;

import ir.ac.kntu.util.Calendar;

public class AddLoanActivity extends AppCompatActivity {
    private Customer customer;
    private List<Loan> loans;
    private EditText name, amount, numOfInstallments;
    private TextView error;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_loan);
        extractAll();
        submit.setOnClickListener(view -> {
            if (name.getText().toString().isEmpty() || amount.getText().toString().isEmpty() || numOfInstallments.getText().toString().isEmpty()) {
                error.setText("Please fill in all fields");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createAlertDialog();
            alertDialog.show();
        });
    }

    private void extractAll() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        loans = MainActivity.getMainBank().getAllLoans().getOrDefault(customer, new ArrayList<Loan>());
        name = findViewById(R.id.editTextText3);
        amount = findViewById(R.id.editTextPhone4);
        numOfInstallments = findViewById(R.id.editTextPhone5);
        error = findViewById(R.id.textView92);
        submit = findViewById(R.id.button6);
    }

    private AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about the " + amount.getText().toString() + " loan request in " + numOfInstallments.getText().toString() + " month?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Loan loan = new Loan(name.getText().toString(), Integer.parseInt(amount.getText().toString()), Integer.parseInt(numOfInstallments.getText().toString()));
                Thread thread = new Thread(new LoanThread(customer, loan));
                thread.start();
                Intent intent = new Intent(AddLoanActivity.this, LoanActivity.class);
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