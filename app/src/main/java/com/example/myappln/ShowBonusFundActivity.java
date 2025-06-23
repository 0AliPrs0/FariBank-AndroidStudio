package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import ir.ac.kntu.util.Calendar;

public class ShowBonusFundActivity extends AppCompatActivity {
    private Customer customer;
    private int position, passedDaysInt;
    private BonusFund fund;
    private TextView name, balance, startTime, period, passedDays;
    private Button withdrawalAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_bonus_fund);
        extractAll();
        name.setText(fund.getName());
        balance.setText(Integer.toString(fund.getBalance()));
        startTime.setText(fund.getStarTime().toString());
        period.setText(Integer.toString(fund.getPeriod()) + " month");
        passedDaysInt = Calendar.distance(Calendar.now(), fund.getStarTime());
        passedDays.setText(Integer.toString(passedDaysInt) + " days have passed");
        if (passedDaysInt < fund.getPeriod() * 30 || fund.getBalance() == 0) {
            withdrawalAll.setAlpha(0.25F);
            return;
        } else {
            withdrawalAll.setOnClickListener(view -> {
                AlertDialog alertDialog = createDialog();
                alertDialog.show();
            });
        }
    }

    private void extractAll() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        position = getIntent().getIntExtra("position", -1);
        fund = (BonusFund) MainActivity.getMainBank().getAllFunds().get(customer).get(position);
        name = findViewById(R.id.textView80);
        balance = findViewById(R.id.textView81);
        startTime = findViewById(R.id.textView82);
        period = findViewById(R.id.textView84);
        passedDays = findViewById(R.id.textView85);
        withdrawalAll = findViewById(R.id.button3);
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about withdrawind total amounth?(" + Integer.toString(fund.getBalance()) + ")");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customer.getCard().increaseBalance(fund.getBalance());
                fund.setBalance(0);
                fund.setEnd(true);
                Intent intent = new Intent(ShowBonusFundActivity.this, FundActivity.class);
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