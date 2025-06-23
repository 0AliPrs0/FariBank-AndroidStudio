package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Instant;

import ir.ac.kntu.util.Calendar;

public class ShowLoanActivity extends AppCompatActivity {
    private Instant now = Calendar.now();
    private Customer customer;
    private int position;
    private Loan loan;
    private TextView name, amount, status, numOfInstallments, numOfInstallmentsPaid, startDate, lastPaid, nextPaid, overdueInstallment, error, howDays;
    private Button installmentPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_loan);
        extractAll();
        setAll();
        if (loan.getStatus() != LoanStatus.ACCEPTED) {
            installmentPayment.setAlpha(0.25F);
            installmentPayment.setEnabled(false);
        } else {
            startDate.setText(loan.getStartLoan().toString());
            if (loan.getNumOfInstallmentsPaid() != 0) {
                lastPaid.setText(loan.getLastPayment().toString());
            }
            if (loan.getNumOfInstallments() != loan.getNumOfInstallmentsPaid()) {
                if (Calendar.daysBetween(now, loan.getNextPayment()) >= 0) {
                    howDays.setText(Integer.toString(Calendar.daysBetween(now, loan.getNextPayment())) + " days installment payment deadline");
                } else {
                    overdueInstallment.setText("You have an overdue installment");
                }
            }
            nextPaid.setText(loan.getNextPayment().toString());
            if (loan.getNumOfInstallments() == loan.getNumOfInstallmentsPaid()) {
                installmentPayment.setAlpha(0.25F);
                installmentPayment.setEnabled(false);
            } else {
                installmentPayment.setOnClickListener(view -> {
                    if (customer.getCard().getBalance() < loan.amountOfInstallment()) {
                        error.setText("Card balance is insufficient");
                        return;
                    }
                    AlertDialog alertDialog = createDialog();
                    alertDialog.show();
                });
            }
        }
    }

    private void extractAll() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        position = getIntent().getIntExtra("position", -1);
        loan = MainActivity.getMainBank().getAllLoans().get(customer).get(position);
        name = findViewById(R.id.textView101);
        amount = findViewById(R.id.textView102);
        status = findViewById(R.id.textView103);
        numOfInstallments = findViewById(R.id.textView104);
        numOfInstallmentsPaid = findViewById(R.id.textView105);
        startDate = findViewById(R.id.textView106);
        lastPaid = findViewById(R.id.textView107);
        nextPaid = findViewById(R.id.textView108);
        overdueInstallment = findViewById(R.id.OverdueInstallment);
        error = findViewById(R.id.error7);
        installmentPayment = findViewById(R.id.button7);
        howDays = findViewById(R.id.textView113);
    }

    private void setAll() {
        name.setText(loan.getName());
        amount.setText(Integer.toString(loan.getAmount()));
        status.setText(loan.getStatus().toString());
        numOfInstallments.setText(Integer.toString(loan.getNumOfInstallments()));
        numOfInstallmentsPaid.setText(Integer.toString(loan.getNumOfInstallmentsPaid()));
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about the payment " + loan.amountOfInstallment() + " as loan installment?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customer.getCard().decreaseBalance(loan.amountOfInstallment());
                loan.payTheInstallment();
                Intent intent = new Intent(ShowLoanActivity.this, LoanActivity.class);
                intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }
}