package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChargeSimcardOrToppingUpActivity extends AppCompatActivity {
    private Button toppingUp, chargeSimcard;
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_charge_simcard_or_topping_up);
        toppingUp = findViewById(R.id.button8);
        chargeSimcard = findViewById(R.id.button9);
        home = findViewById(R.id.imageView30);
        toppingUp.setOnClickListener(view -> {
            Intent intent = new Intent(ChargeSimcardOrToppingUpActivity.this, ToppingUpActivity.class);
            intent.putExtra("phoneNumber", getIntent().getStringExtra("phoneNumber"));
            startActivity(intent);
        });
        chargeSimcard.setOnClickListener(view -> {
            Intent intent = new Intent(ChargeSimcardOrToppingUpActivity.this, ChargeSimcardActivity.class);
            intent.putExtra("phoneNumber", getIntent().getStringExtra("phoneNumber"));
            startActivity(intent);
        });
        home.setOnClickListener(view -> {
            Intent intent = new Intent(ChargeSimcardOrToppingUpActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", getIntent().getStringExtra("phoneNumber"));
            startActivity(intent);
        });
    }
}