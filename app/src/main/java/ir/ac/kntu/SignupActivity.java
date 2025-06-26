package ir.ac.kntu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText nationalId;
    private EditText password;
    private EditText confirmPassword;
    private TextView errors;
    private Button signup;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        phoneNumber = findViewById(R.id.phone_number);
        nationalId = findViewById(R.id.national_id);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        errors = findViewById(R.id.errors);
        signup = findViewById(R.id.signup);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
        });

        List<String> labels = errorLabels();

        Bank bank = MainActivity.getMainBank();

        signup.setOnClickListener(v -> {
            String newFName = String.valueOf(firstName.getText());
            String newLName = String.valueOf(lastName.getText());
            String newId = String.valueOf(nationalId.getText());
            String newPhone = String.valueOf(phoneNumber.getText());
            String newPass = String.valueOf(password.getText());
            String newConfPass = String.valueOf(confirmPassword.getText());
            errors.setTextColor(Color.parseColor("#000000"));
            if (newFName.isEmpty() || newLName.isEmpty() || newPhone.isEmpty() || newId.isEmpty() || newPass.isEmpty() || newConfPass.isEmpty()) {
                errors.setText(labels.get(0));
            } else if (!checkPhoneNumberFormat(newPhone)) {
                errors.setText(labels.get(1));
            } else if (!checkPhoneNumber(bank, newPhone)) {
                errors.setText(labels.get(2));
            } else if (!checkNationalIdFormat(newId)) {
                errors.setText(labels.get(3));
            } else if (!checkId(bank, newId)) {
                errors.setText(labels.get(4));
            } else if (!checkPassword(newPass)) {
                errors.setText(labels.get(5));
            } else if (!newPass.equals(newConfPass)) {
                errors.setText(labels.get(6));
            } else {
                User user = new User(newFName, newLName, newPhone, newId, newPass);
                addUser(bank, user);
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
        });


    }

    public List<String> errorLabels() {
        List<String> labels = new ArrayList<>();

        String empty = "All fields are required!";
        String inCorrectPhone = "The format of phone number is not correct!";
        String samePhone = "There is a same phone number!";
        String inCorrectId = "The format of id is not correct!";
        String sameId = "There is a same id!";
        String unsafePass = "Password is unsafe, The password must be a combination of numbers, lowercase letters, uppercase letters and special characters!";
        String isNotSamePass = "Passwords do not match";

        labels.add(empty);
        labels.add(inCorrectPhone);
        labels.add(samePhone);
        labels.add(inCorrectId);
        labels.add(sameId);
        labels.add(unsafePass);
        labels.add(isNotSamePass);

        return labels;
    }

    public void addUser(Bank myBank, User newUser) {
        final String fName = newUser.getFirstName();
        final String lName = newUser.getLastName();
        final String phoneNumber = newUser.getPhoneNumber();
        final String nationalId = newUser.getNationalId();
        final String password = newUser.getPassword();

        int min = 10_000_000;
        int max = 99_999_999;
        final int accountNumber = ThreadLocalRandom.current().nextInt(min, max + 1);

        final UserAccount user = new UserAccount(fName, lName, phoneNumber, nationalId, password, 0, String.valueOf(accountNumber));
        myBank.getUserAccounts().add(user);
    }

    public boolean checkPhoneNumber(Bank myBank, String phoneNumber) {
        if (myBank.getUserAccounts() == null) {
            return true;
        }

        for (final UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPhoneNumberFormat(String phoneNumber) {
        String patternStr = "\\d{11}";
        final Pattern pattern = Pattern.compile(patternStr);
        final Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean checkId(Bank myBank, String nationalId) {
        for (final UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getNationalId().equals(nationalId)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNationalIdFormat(String nationalId) {
        String patternStr = "\\d{10}";
        final Pattern pattern = Pattern.compile(patternStr);
        final Matcher matcher = pattern.matcher(nationalId);
        return matcher.matches();
    }

    public boolean checkPassword(String password) {
        String pSmallLetter = "[a-z]+";
        final boolean isSmallLetter = findRegex(pSmallLetter, password);

        String pCapitalLetter = "[A-Z]+";
        final boolean isCapitalLetter = findRegex(pCapitalLetter, password);

        String pDigit = "\\d+";
        final boolean isDigit = findRegex(pDigit, password);

        String pCharacter = "[@!#$%^]+";
        final boolean isCharacter = findRegex(pCharacter, password);

        return isSmallLetter && isCapitalLetter && isDigit && isCharacter;
    }

    public boolean findRegex(String patternStr, String password) {
        final Pattern pattern = Pattern.compile(patternStr);
        final Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

/*package ir.ac.kntu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button login;
    private Button signup;
    private TextView success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        success = findViewById(R.id.success);

        login.setOnClickListener(v -> {
            String strUser = userName.getText().toString();
            String strPass = password.getText().toString();
            success.setTextColor(Color.parseColor("#000000"));
            if (strUser.equals("ali") && strPass.equals("prs")) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);

                success.setText("success login");
            } else {
                success.setText("failed login");
            }
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });

    }

}*/
