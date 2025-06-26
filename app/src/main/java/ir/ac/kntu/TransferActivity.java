package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

import java.util.concurrent.atomic.AtomicBoolean;

public class TransferActivity extends AppCompatActivity {
    private Button transfer, check;
    private ImageView home;
    private EditText money, accountNumber;
    private TextView isNotEnoughMoney, destinAccount;


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Bank bank = MainActivity.getMainBank();
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        UserAccount user = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);

        setViews();


        home.setOnClickListener(v -> {
            Intent intent = new Intent(TransferActivity.this, DashBoardActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        AtomicBoolean isThereUser = new AtomicBoolean(false);

        check.setOnClickListener(v -> {
        UserAccount destinationAcc = findAccount(accountNumber.getText().toString(), bank);
            if (destinationAcc != null) {
                isThereUser.set(true);
                destinAccount.setTextColor(Color.parseColor("#37B309"));
                destinAccount.setText(String.format("Destination account: %s %s" ,destinationAcc.getFirstName(), destinationAcc.getLastName()));
            } else {
                isThereUser.set(false);
                destinAccount.setTextColor(Color.parseColor("#CD1F12"));
                destinAccount.setText("Not fund!");
            }
        });


        transfer.setOnClickListener(v -> {
            isNotEnoughMoney.setText(accountNumber.getText());
            UserAccount destinationAcc = findAccount(accountNumber.getText().toString(), bank);
            int amount = 0;
            try {
                amount = Integer.parseInt(money.getText().toString());
            } catch (Exception e) {
                isNotEnoughMoney.setTextColor(Color.parseColor("#CD1F12"));
                isNotEnoughMoney.setText("Invalid input!");
            }

            if (destinationAcc != null){
                if (amount + 1000 <= user.getBalanceAccount() && amount != 0) {
                    bank.addTransfer(new Transfer(amount, user, destinationAcc));
                    Intent intent = new Intent(TransferActivity.this, DashBoardActivity.class);
                    intent.putExtra("phoneNumber", user.getPhoneNumber());
                    startActivity(intent);
                } else {
                    isNotEnoughMoney.setTextColor(Color.parseColor("#CD1F12"));
                    isNotEnoughMoney.setText("Your money is not enough!");
                }
            }
        });





    }

    private void setViews() {
        home = findViewById(R.id.home);
        transfer = findViewById(R.id.transfer);
        check = findViewById(R.id.check);
        money = findViewById(R.id.money_amount);
        accountNumber = findViewById(R.id.account_num);
        destinAccount = findViewById(R.id.destination_account);
        isNotEnoughMoney = findViewById(R.id.not_money);
    }

    @SuppressLint("DefaultLocale")
    private UserAccount findAccount(String accountNumberDist, Bank bank){
        for (UserAccount user : bank.getUserAccounts()) {
            if (accountNumberDist.equals(user.getAccountNumber())) {
                return user;
            }
        }
        return null;
    }
}