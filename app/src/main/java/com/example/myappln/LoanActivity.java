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

public class LoanActivity extends AppCompatActivity {
    private Customer customer;
    private List<Loan> loans;
    private RecyclerView recyclerView;
    private ImageView home, add;
    private TextView error;
    private LoanAdaptor adaptor;
    private LoanAdaptor.LoanViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan);
        extractAll();
        if (loans.isEmpty()) {
            error.setText("You dont have loan");
        } else {
            error.setText("");
        }
        setAdaptor();
        add.setOnClickListener(view -> {
            Intent intent = new Intent(LoanActivity.this, AddLoanActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        home.setOnClickListener(view -> {
            Intent intent = new Intent(LoanActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }

    private void extractAll() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        loans = MainActivity.getMainBank().getAllLoans().getOrDefault(customer, new ArrayList<>());
        recyclerView = findViewById(R.id.loanList);
        home = findViewById(R.id.home6);
        add = findViewById(R.id.addLoan);
        error = findViewById(R.id.errorEmpty4);
    }

    private void setAdaptor() {
        setOnClickerListener();
        adaptor = new LoanAdaptor(loans, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    private void setOnClickerListener() {
        listener = new LoanAdaptor.LoanViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(LoanActivity.this, ShowLoanActivity.class);
                intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        };
    }
}