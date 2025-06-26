package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

public class DashBoardActivity extends AppCompatActivity {
    private ImageView logout;
    private ImageView chargeAccount;
    private ImageView loan;
    private ImageView transfer;
    private ImageView contact;
    private ImageView fund;
    private TextView name;
    private TextView accountNumber;
    private TextView money;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bank bank = MainActivity.getMainBank();
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        UserAccount user = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);

        setViews(user);

        logout.setOnClickListener(v -> {
            startActivity(new Intent(DashBoardActivity.this, MainActivity.class));
        });

        chargeAccount.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, ChargeAccountActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        loan.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, LoanActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        transfer.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, TransferActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        contact.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, ContactActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        fund.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, ChargeAccountActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void setViews(UserAccount user) {
        logout = findViewById(R.id.logout);
        chargeAccount = findViewById(R.id.charge_account);
        loan = findViewById(R.id.loan);
        transfer = findViewById(R.id.transfer);
        contact = findViewById(R.id.contact);
        fund = findViewById(R.id.fund);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        accountNumber = findViewById(R.id.account_number);
        money = findViewById(R.id.amount_money);

        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        money.setText(String.format("%,d $", user.getBalanceAccount()));
        accountNumber.setText(user.getAccountNumber());
    }


}