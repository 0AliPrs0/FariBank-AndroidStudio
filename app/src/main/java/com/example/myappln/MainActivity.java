package com.example.myappln;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ir.ac.kntu.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static NeoBank neoBank = new NeoBank();
    private EditText phoneNumber;
    private EditText password;
    private Button login;
    private Button signup;
    private TextView errorMassage;

    public static NeoBank getMainBank() {
        return neoBank;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Customer customer1 = new Customer("Amir", "Baghri", "09102189822", "0200583271", "A.1.a");
        Customer customer2 = new Customer("Meraj", "Gilvari", "09122163863", "1234567890", "A.1.a");
        customer1.getContacts().add(new Contact("meraj", "Gilvari", customer2));
        neoBank.addToRegisters(customer1);
        neoBank.addToRegisters(customer2);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.sign_up);
        phoneNumber = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        errorMassage = findViewById(R.id.error_massage);
        login.setOnClickListener(view -> {
            errorMassage.setTextColor(Color.parseColor("#FF0000"));
            if (phoneNumber.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                errorMassage.setText("Please fill in all fields");
                return;
            }
            if (!Person.checkPhoneNumber(phoneNumber.getText().toString())) {
                errorMassage.setText("Please enter the correct mobile number");
                return;
            }
            Customer customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(phoneNumber.getText().toString());
            if (neoBank.getNotRegisters().contains(customer)) {
                errorMassage.setText("You are waiting for the administrator's approval");
                return;
            } else if (neoBank.getRegisters().contains(customer)) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("phoneNumber", phoneNumber.getText().toString());
                startActivity(intent);
                errorMassage.setText("");
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                return;
            } else if (MainActivity.getMainBank().havePhoneNumber(phoneNumber.getText().toString())) {
                errorMassage.setText("The password entered is incorrect");
                return;
            } else {
                errorMassage.setText("The information entered is incorrect");
                return;
            }
        });
        signup.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });
    }

    public static int digits(int num) {
        int digits = 0;
        for (int i = num; i > 0; i /= 10) {
            digits++;
        }
        return digits;
    }
}