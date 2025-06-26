package ir.ac.kntu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.ImageView;
import com.example.loginpage.R;

public class LoanRequestActivity extends AppCompatActivity {
    private Bank bank;
    private UserAccount userAccount;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        userAccount = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);
        bank = MainActivity.getMainBank();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listView = findViewById(R.id.listViewLoans);
        ArrayList<String> loanItems = new ArrayList<>();
        for (Loan loan : userAccount.getLoans()) {
            String str = "Loan status='" + loan.getLoanStatus() + "', Amount loan=" + loan.getAmountLoan();
            loanItems.add(str);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, loanItems);
        listView.setAdapter(adapter);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button sendButton = findViewById(R.id.sendButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button backButton = findViewById(R.id.backButton);

        sendButton.setOnClickListener(view -> {
            showAddLoanDialog();
        });

        backButton.setOnClickListener(view -> {
            finish();
        });



    }

    private void showAddLoanDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_loan, null);

        final EditText editTextAmount = dialogView.findViewById(R.id.editTextAmount);
        final Spinner spinnerInstallments = dialogView.findViewById(R.id.spinnerInstallments);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.installment_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstallments.setAdapter(adapter);

        new AlertDialog.Builder(this)
                .setTitle("Confirm Loan")
                .setView(dialogView)
                .setPositiveButton("Add Loan", (dialogInterface, i) -> {
                    int amount = Integer.parseInt(editTextAmount.getText().toString());
                    int installments = Integer.parseInt(spinnerInstallments.getSelectedItem().toString());
                    addLoan(amount, installments);
                    // Refresh the activity
                    recreate();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void addLoan(int amountLoan, int numInstallment) {
        Date date = new Date();
        userAccount.addLoans(new Loan(amountLoan, numInstallment, date));
    }
}
