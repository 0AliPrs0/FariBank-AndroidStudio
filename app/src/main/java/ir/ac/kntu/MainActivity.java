package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

public class MainActivity extends AppCompatActivity {
    private static Bank neoBank = new Bank();
    private EditText userName;
    private EditText password;
    private Button login;
    private Button signup;
    private TextView success;
    private ImageButton showPasswordButton;
    private boolean isPasswordVisible = false;

    public static Bank getMainBank() {
        return neoBank;
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleTreads(neoBank);

        userName = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        success = findViewById(R.id.success);
        showPasswordButton = findViewById(R.id.showPasswordButton);
        neoBank.addUserAccount(new UserAccount("Ali", "prs", "09032948208", "1452006601", "@Ap84", 0, "12345678"));

        login.setOnClickListener(v -> {
            String strUser = userName.getText().toString();
            String strPass = password.getText().toString();
            UserAccount user = findUser(neoBank, strUser, strPass);
            if (user != null) {
                Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                intent.putExtra("phoneNumber", userName.getText().toString());
                startActivity(intent);
                success.setTextColor(Color.parseColor("#0EA314"));
                success.setText("success login...");
            } else {
                success.setTextColor(Color.parseColor("#E80505"));
                success.setText("failed login!");
            }
        });

        showPasswordButton.setOnClickListener(v -> {
            if (isPasswordVisible) {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showPasswordButton.setImageResource(R.drawable.show_password);
            } else {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                showPasswordButton.setImageResource(R.drawable.hide_password);
            }
            isPasswordVisible = !isPasswordVisible;
            password.setSelection(password.getText().length());
        });

        signup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });

    }

    public UserAccount findUser(Bank bank, String phoneNumber, String password) {
        for (UserAccount entry : bank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        return null;
    }

        public static void handleTreads(Bank bank){
        final TransferThread thread1 = new TransferThread(bank);
        thread1.start();

        final RequestThread thread2 = new RequestThread(bank);
        thread2.start();

        final FundThread thread3 = new FundThread(bank);
        thread3.start();

        final LoanThread thread4 = new LoanThread(bank);
        thread4.start();
    }

}