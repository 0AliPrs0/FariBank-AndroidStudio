package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

public class ChargeAccountActivity extends AppCompatActivity {
    private Button pay;
    private ImageView home;
    private EditText money;
    private TextView invalid;
    private TextView successful;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_account);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        UserAccount user = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);

        setViews();


        home.setOnClickListener(v -> {
            Intent intent = new Intent(ChargeAccountActivity.this, DashBoardActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        pay.setOnClickListener(v -> {
            int amount;
            try {
                amount = Integer.parseInt(money.getText().toString());
                user.setBalanceAccount(user.getBalanceAccount() + amount);
                successful.setText("Successful payment");
                invalid.setText("");
            } catch (Exception e) {
                successful.setText("");
                invalid.setText("Invalid input");
            }
        });


    }

    private void setViews() {
        home = findViewById(R.id.home);
        invalid = findViewById(R.id.invalid);
        pay = findViewById(R.id.pay);
        money = findViewById(R.id.money);
        successful = findViewById(R.id.successful);
    }


}