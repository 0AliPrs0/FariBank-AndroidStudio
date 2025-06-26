package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

import java.util.ArrayList;

public class LoanManagementActivity extends AppCompatActivity {
    private Bank bank;
    private UserAccount userAccount;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_management);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        userAccount = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);
        bank = MainActivity.getMainBank();

        ListView loanListView = findViewById(R.id.loanListView);
        Button viewDetailsButton = findViewById(R.id.viewDetailsButton);
        Button payInstallmentButton = findViewById(R.id.payInstallmentButton);
        Button backButton = findViewById(R.id.backButton);

        ArrayList<Loan> acceptedLoans = new ArrayList<>();
        for (Loan loan : userAccount.getLoans()) {
            if (loan.getLoanStatus() == LoanStatus.ACCEPTED) {
                acceptedLoans.add(loan);
            }
        }

        ArrayAdapter<Loan> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, acceptedLoans);
        loanListView.setAdapter(adapter);
        loanListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        viewDetailsButton.setOnClickListener(view -> {
            int selectedPosition = loanListView.getCheckedItemPosition();
            if (selectedPosition != ListView.INVALID_POSITION) {
                Loan selectedLoan = (Loan) loanListView.getItemAtPosition(selectedPosition);
                showLoanDetails(selectedLoan);
            } else {
                showToast("No loan selected. Please select a loan to view details.");
            }
        });

        payInstallmentButton.setOnClickListener(view -> {
            int selectedPosition = loanListView.getCheckedItemPosition();
            if (selectedPosition != ListView.INVALID_POSITION) {
                Loan selectedLoan = (Loan) loanListView.getItemAtPosition(selectedPosition);
                payInstallment(selectedLoan);
            } else {
                showToast("No loan selected. Please select a loan to pay installment.");
            }
        });

        backButton.setOnClickListener(view -> finish());
    }

    private void showLoanDetails(Loan loan) {
        Intent intent = new Intent(this, LoanDetailsActivity.class);
        intent.putExtra("loan", loan);  // Serializable transfer
        startActivity(intent);
    }

    private void payInstallment(Loan loan) {
        Intent intent = new Intent(this, PayInstallmentActivity.class);
        intent.putExtra("loan", loan);  // Serializable transfer
        intent.putExtra("userAccount", userAccount);  // Serializable transfer
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
