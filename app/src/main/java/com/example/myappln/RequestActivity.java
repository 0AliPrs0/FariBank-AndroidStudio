package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

public class RequestActivity extends AppCompatActivity {
    private Customer customer;
    private ImageView add, home;
    private TextView error;
    private RecyclerView recyclerView;
    private List<Request> requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request);
        extractAll();
        requests = customer.getRequests();
        if (requests.isEmpty()) {
            error.setText("You dont have requests");
        } else {
            error.setText("");
        }
        setAdaptor();
        home.setOnClickListener(view -> {
            Intent intent = new Intent(RequestActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
        add.setOnClickListener(view -> {
            Intent intent = new Intent(RequestActivity.this, AddRequestActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }

    private void setAdaptor() {
        RequestAdaptor adaptor = new RequestAdaptor(requests);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    private void extractAll() {
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        add = findViewById(R.id.addRequest);
        home = findViewById(R.id.home5);
        error = findViewById(R.id.errorEmoty2);
        recyclerView = findViewById(R.id.requests);
    }
}