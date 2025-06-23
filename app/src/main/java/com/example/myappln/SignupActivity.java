package com.example.myappln;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    private EditText firstName, lastName, phoneNumber, nationalCode, password;
    private Button submit;
    private TextView massage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        extract();
        submit.setOnClickListener(view -> {
            massage.setTextColor(Color.parseColor("#FF0000"));
            if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || nationalCode.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                massage.setText("Please fill in all fields");
                return;
            }
            if (!check()) {
                return;
            }
            Customer customer = new Customer(firstName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString(), nationalCode.getText().toString(), password.getText().toString());
            if (MainActivity.getMainBank().getNotRegisters().contains(customer)) {
                massage.setText("You are waiting for the administrator's approval");
                return;
            } else if (MainActivity.getMainBank().getRegisters().contains(customer)) {
                massage.setText("You are registered");
                return;
            } else if (MainActivity.getMainBank().havePhoneNumber(phoneNumber.getText().toString())) {
                massage.setText("Phone number is already registered in the system");
                return;
            } else if (MainActivity.getMainBank().haveNationalCode(nationalCode.getText().toString())) {
                massage.setText("National code is already registered in the system");
                return;
            } else {
                massage.setTextColor(Color.parseColor("#59FF00"));
                massage.setText("Your information has been registered");
                MainActivity.getMainBank().addToNotRegisters(customer);
                Thread signupThread = new Thread(new SignupThread(customer));
                signupThread.start();
            }
        });
    }

    private void extract() {
        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        phoneNumber = findViewById(R.id.PhoneNumber);
        nationalCode = findViewById(R.id.NationalCode);
        password = findViewById(R.id.Password);
        submit = findViewById(R.id.Submit);
        massage = findViewById(R.id.error_massage);
    }

    private boolean check() {
        if (!Person.checkPhoneNumber(phoneNumber.getText().toString())) {
            massage.setText("Your phone number is incorrect");
            return false;
        }
        if (!Person.checkNationalCode(nationalCode.getText().toString())) {
            massage.setText("Your national code is incorrect");
            return false;
        }
        if (!Person.checkPassword(password.getText().toString())) {
            massage.setText("Your password is not safe");
            return false;
        }
        massage.setText("");
        return true;
    }
}