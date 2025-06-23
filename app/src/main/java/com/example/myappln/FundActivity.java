package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FundActivity extends AppCompatActivity {
    private Customer customer;
    private RecyclerView recyclerView;
    private ImageView home, add;
    private TextView error;
    private List<Fund> funds;
    private FundAdaptor.FundViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fund);
        extractAndSet();
        setAdaptor();
        if (funds.isEmpty()) {
            error.setText("You have no fund");
        }
        home.setOnClickListener(view -> {
            Intent intent = new Intent(FundActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        add.setOnClickListener(view -> {
            Intent intent = new Intent(FundActivity.this, AddFundActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }

    private void extractAndSet() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        recyclerView = findViewById(R.id.recyclerView);
        home = findViewById(R.id.home4);
        add = findViewById(R.id.addFunds);
        error = findViewById(R.id.errorEmpty3);
        funds = MainActivity.getMainBank().getAllFunds().getOrDefault(customer, new ArrayList<Fund>());
    }

    private void setAdaptor() {
        setOnClicker();
        FundAdaptor adaptor = new FundAdaptor(funds, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    private void setOnClicker() {
        listener = new FundAdaptor.FundViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;
                Fund wantedFund = funds.get(position);
                if (wantedFund instanceof SavingFund) {
                    intent = new Intent(FundActivity.this, ShowSavingFundActivity.class);
                } else {
                    intent = new Intent(FundActivity.this, ShowBonusFundActivity.class);
                }
                intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        };
    }
}