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

public class ShowSavingFundActivity extends AppCompatActivity {
    private Customer customer;
    private int position;
    private SavingFund fund;
    private TextView name, balance, error;
    private Button withdrawal, deposit, total;
    private EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_saving_fund);
        extractAll();
        deposit.setOnClickListener(view -> {
            if (amount.getText().toString().isEmpty()) {
                error.setText("Please enter the amount");
                return;
            }
            if (customer.getCard().getBalance() < Integer.parseInt(amount.getText().toString())) {
                error.setText("Card balance is insufficient");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createDialogForWithdrawalOrDeposit(1, Integer.parseInt(amount.getText().toString()));
            alertDialog.show();
        });
        withdrawal.setOnClickListener(view -> {
            if (amount.getText().toString().isEmpty()) {
                error.setText("Please enter the amount");
                return;
            }
            if (fund.getBalance() < Integer.parseInt(amount.getText().toString())) {
                error.setText("Fund balance is insufficient");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createDialogForWithdrawalOrDeposit(2, Integer.parseInt(amount.getText().toString()));
            alertDialog.show();
        });
        total.setOnClickListener(view -> {
            if (fund.getBalance() == 0) {
                error.setText("There is no money in the fund");
                return;
            }
            AlertDialog alertDialog = createDialogForTotal();
            alertDialog.show();
        });
    }

    private void extractAll() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        position = getIntent().getIntExtra("position", -1);
        fund = (SavingFund) MainActivity.getMainBank().getAllFunds().get(customer).get(position);
        name = findViewById(R.id.textView76);
        name.setText(fund.getName());
        balance = findViewById(R.id.textView77);
        balance.setText(Integer.toString(fund.getBalance()));
        withdrawal = findViewById(R.id.withdrawal);
        deposit = findViewById(R.id.deposit);
        total = findViewById(R.id.total);
        amount = findViewById(R.id.editTextPhone);
        error = findViewById(R.id.error5);
    }

    private AlertDialog createDialogForTotal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about withdrawing the total amount?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customer.getCard().increaseBalance(fund.getBalance());
                fund.setBalance(0);
                Intent intent = new Intent(ShowSavingFundActivity.this, FundActivity.class);
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

    private AlertDialog createDialogForWithdrawalOrDeposit(int condition, int amount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (condition == 1) {
            builder.setMessage("Are you sure about depositing " + amount + " to the fund?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    customer.getCard().decreaseBalance(amount);
                    fund.increaseBalance(amount);
                    Intent intent = new Intent(ShowSavingFundActivity.this, FundActivity.class);
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
        } else {
            builder.setMessage("Are you sure about withdrawing " + amount + " from the fund?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    customer.getCard().increaseBalance(amount);
                    fund.decreaseBalance(amount);
                    Intent intent = new Intent(ShowSavingFundActivity.this, FundActivity.class);
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
        }
        return builder.create();
    }
}