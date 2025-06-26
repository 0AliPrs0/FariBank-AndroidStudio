package ir.ac.kntu;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

import java.util.ArrayList;

public class LoanDetailsActivity extends AppCompatActivity {
    private Loan loan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);

        loan = (Loan) getIntent().getSerializableExtra("loan");

        ListView installmentListView = findViewById(R.id.installmentListView);
        ArrayList<String> installmentDetails = new ArrayList<>();
        for (Installment installment : loan.getInstallments()) {
            String str = "Installment Amount: " + installment.getAmountInstallment() +
                    ", Due Date: " + installment.getPayDate() +
                    ", Status: " + installment.getStatus();
            installmentDetails.add(str);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, installmentDetails);
        installmentListView.setAdapter(adapter);
    }
}
