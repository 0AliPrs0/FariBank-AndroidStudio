package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private String phoneNumber;
    private Customer customer;
    private ImageView home, transfer, loan, contact, setting, add, fund, logout, cardButton;
    private TextView fullName, balance;
    private RecyclerView recyclerView;
    private TransactionAdaptor.TransactionViewClickListener listener;
    private List<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        extractAll();
        setAll();
        home.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        cardButton.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, ShowInformationActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        add.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, ChargeSimcardOrToppingUpActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        logout.setOnClickListener(view -> {
            createDialog().show();
        });
        contact.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, ContactActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        transfer.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, TransferActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        setting.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, SettingActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        fund.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, FundActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        loan.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, LoanActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
    }

    private void extractAll() {
        home = findViewById(R.id.homeButton);
        transfer = findViewById(R.id.transfer);
        loan = findViewById(R.id.loan);
        contact = findViewById(R.id.contacts);
        setting = findViewById(R.id.setting);
        add = findViewById(R.id.toppingUp);
        fund = findViewById(R.id.funds);
        logout = findViewById(R.id.logout);
        fullName = findViewById(R.id.fullName);
        balance = findViewById(R.id.balance);
        cardButton = findViewById(R.id.cardButton);
        recyclerView = findViewById(R.id.transactions);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(phoneNumber);
    }

    private void setAll() {
        fullName.setText(customer.getFirstName() + " " + customer.getLastName());
        balance.setText(Integer.toString(customer.getCard().getBalance()));
        transactions = MainActivity.getMainBank().getTransactions().getOrDefault(customer, new ArrayList<>());
        setAdaptor();
    }

    private void setAdaptor() {
        setOnClicker();
        TransactionAdaptor adaptor = new TransactionAdaptor(transactions, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    private void setOnClicker() {
        listener = new TransactionAdaptor.TransactionViewClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ShowTransactionActivity.class);
                intent.putExtra("trackingNumber", transactions.get(position).getTrackingNumber());
                startActivity(intent);
            }
        };
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
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