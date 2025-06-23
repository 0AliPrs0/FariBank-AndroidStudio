package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private Customer customer;
    private TextView supportRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        supportRequest = findViewById(R.id.textView60);
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
        supportRequest.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this, RequestActivity.class);
            intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
            startActivity(intent);
        });
    }
}