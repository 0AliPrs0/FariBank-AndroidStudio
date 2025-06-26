package ir.ac.kntu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;

import java.util.ArrayList;
import java.util.Date;

public class PayInstallmentActivity extends AppCompatActivity {
    private Loan loan;
    private UserAccount userAccount;
    private ArrayAdapter<Installment> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_installment);

        loan = (Loan) getIntent().getSerializableExtra("loan");
        userAccount = (UserAccount) getIntent().getSerializableExtra("userAccount");

        ListView installmentListView = findViewById(R.id.installmentListView);
        ArrayList<Installment> overdueInstallments = new ArrayList<>();
        Date currentDate = new Date();

        for (Installment installment : loan.getInstallments()) {
            if (installment.getPayDate().getTime() < currentDate.getTime() && installment.getStatus() == InstallmentStatus.OVERDUE) {
                overdueInstallments.add(installment);
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, overdueInstallments);
        installmentListView.setAdapter(adapter);
        installmentListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(view -> {
            int selectedPosition = installmentListView.getCheckedItemPosition();
            if (selectedPosition != ListView.INVALID_POSITION) {
                Installment selectedInstallment = overdueInstallments.get(selectedPosition); // استفاده از لیست اورجینال برای انتخاب قسط

                Log.d("PayInstallment", "Selected installment: " + selectedInstallment.toString());

                if (userAccount.getBalanceAccount() >= selectedInstallment.getAmountInstallment()) {
                    Log.d("PayInstallment", "Balance before payment: " + userAccount.getBalanceAccount());

                    userAccount.setBalanceAccount(userAccount.getBalanceAccount() - selectedInstallment.getAmountInstallment());

                    selectedInstallment.setStatus(InstallmentStatus.PAID);

                    Log.d("PayInstallment", "Balance after payment: " + userAccount.getBalanceAccount());
                    Log.d("PayInstallment", "Installment status after payment: " + selectedInstallment.getStatus());

                    overdueInstallments.set(selectedPosition, selectedInstallment);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(this, "Installment paid successfully.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Insufficient balance to pay this installment.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please select an overdue installment.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
