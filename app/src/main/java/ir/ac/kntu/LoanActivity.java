package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

public class LoanActivity extends AppCompatActivity {
    private Button loanRequest, loanManagement;
    private ImageView home;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        UserAccount user = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);

        setViews();


        home.setOnClickListener(v -> {
            Intent intent = new Intent(LoanActivity.this, DashBoardActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        loanRequest.setOnClickListener(v -> {
            Intent intent = new Intent(LoanActivity.this, LoanRequestActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        loanManagement.setOnClickListener(v -> {
            Intent intent = new Intent(LoanActivity.this, LoanManagementActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });


    }

    private void setViews() {
        home = findViewById(R.id.home);
        loanRequest = findViewById(R.id.loan_request);
        loanManagement = findViewById(R.id.loan_management);
    }


}