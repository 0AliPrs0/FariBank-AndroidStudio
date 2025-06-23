package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ir.ac.kntu.util.Calendar;

public class AddFundActivity extends AppCompatActivity {
    private Customer customer;
    private Spinner spinner;
    private FundType[] fundTypes = {FundType.SAVING_FUND, FundType.BONUS_FUND};
    private FundType fundType;
    private TextView monthTextView, error;
    private EditText name, amount, period;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_fund);
        extractAll();
        setSpinner();
        submit.setOnClickListener(view -> {
            if (fundType == FundType.SAVING_FUND) {
                if (name.getText().toString().isEmpty() || amount.getText().toString().isEmpty()) {
                    error.setText("Please fill in all fields");
                    return;
                }
                if (customer.getCard().getBalance() < Integer.parseInt(amount.getText().toString())) {
                    error.setText("Card balance is insufficient");
                    return;
                }
                error.setText("");
                AlertDialog alertDialog = createDialogForSavingFund();
                alertDialog.show();
            } else {
                if (name.getText().toString().isEmpty() || amount.getText().toString().isEmpty() || period.getText().toString().isEmpty()) {
                    error.setText("Please fill in all fields");
                    return;
                }
                if (customer.getCard().getBalance() < Integer.parseInt(amount.getText().toString())) {
                    error.setText("Card balance is insufficient");
                    return;
                }
                error.setText("");
                AlertDialog alertDialog = createDialogForBonusFund();
                alertDialog.show();
            }
        });
    }

    private void extractAll() {
        monthTextView = findViewById(R.id.textView87);
        name = findViewById(R.id.editTextText);
        amount = findViewById(R.id.editTextPhone2);
        period = findViewById(R.id.editTextPhone3);
        submit = findViewById(R.id.button5);
        error = findViewById(R.id.error6);
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
    }

    private void setSpinner() {
        spinner = findViewById(R.id.spinner2);
        ArrayAdapter<FundType> adapter = new ArrayAdapter<FundType>(AddFundActivity.this, android.R.layout.simple_spinner_item, fundTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fundType = fundTypes[position];
                if (position == 0) {
                    monthTextView.setAlpha(0);
                    period.setAlpha(0);
                    period.setEnabled(false);
                } else {
                    monthTextView.setAlpha(1);
                    period.setAlpha(1);
                    period.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                fundType = FundType.SAVING_FUND;
                monthTextView.setAlpha(1);
                period.setAlpha(0);
                period.setEnabled(false);
            }
        });
    }

    private AlertDialog createDialogForSavingFund() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about making a savings fund " + name.getText().toString() + " with the amount " + amount.getText().toString() + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customer.getCard().decreaseBalance(Integer.parseInt(amount.getText().toString()));
                List<Fund> customerFunds = MainActivity.getMainBank().getAllFunds().getOrDefault(customer, new ArrayList<>());
                customerFunds.add(new SavingFund(name.getText().toString(), Integer.parseInt(amount.getText().toString())));
                Intent intent = new Intent(AddFundActivity.this, FundActivity.class);
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

    private AlertDialog createDialogForBonusFund() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about making a bonus fund " + name.getText().toString() + " with the amount " + amount.getText().toString() + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customer.getCard().decreaseBalance(Integer.parseInt(amount.getText().toString()));
                List<Fund> customerFunds = MainActivity.getMainBank().getAllFunds().getOrDefault(customer, new ArrayList<>());
                BonusFund bonusFund = new BonusFund(name.getText().toString(), Integer.parseInt(amount.getText().toString()), Integer.parseInt(period.getText().toString()), Calendar.now());
                customerFunds.add(bonusFund);
                MainActivity.getMainBank().getAllFunds().put(customer,(ArrayList) customerFunds);
                Thread thread = new Thread(new BonusFundThread(bonusFund));
                thread.start();
                Intent intent = new Intent(AddFundActivity.this, FundActivity.class);
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