package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CompleteTransferActivity extends AppCompatActivity {
    private Customer sender, receiver;
    private EditText amount;
    private Button instantTransfer, transferIn12;
    private TextView error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_transfer);
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        //Tof too zatet
        receiver = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("receiverPhoneNumber"));
        extractAll();
        instantTransfer.setOnClickListener(view -> {
            if (amount.getText().toString().isEmpty()) {
                error.setText("Please enter the amount");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createDialogForInstantTransfer();
            alertDialog.show();
        });
        transferIn12.setOnClickListener(view -> {
            if (amount.getText().toString().isEmpty()) {
                error.setText("Please enter the amount");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createDialogForTransferIn12();
            alertDialog.show();
        });
    }

    private void extractAll() {
        amount = findViewById(R.id.transferAmount);
        instantTransfer = findViewById(R.id.instantTransfer);
        transferIn12 = findViewById(R.id.transferin12);
        error = findViewById(R.id.error3);
    }

    private AlertDialog createDialogForInstantTransfer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int transferAmount = Integer.parseInt(amount.getText().toString());
        builder.setMessage("Do you confirm the transfer of " + transferAmount + " to " + receiver.getFirstName() + " " + receiver.getLastName() + " using the instant transfer method?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int fee = transferAmount / 100;
                Intent intent = new Intent(CompleteTransferActivity.this, ConfirmTransferActivity.class);
                intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
                intent.putExtra("receiverPhoneNumber", receiver.getSimCard().getPhoneNumber());
                intent.putExtra("amount", transferAmount);
                intent.putExtra("fee", fee);
                intent.putExtra("confirm", transferAmount + fee <= sender.getCard().getBalance());
                startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    private AlertDialog createDialogForTransferIn12() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int transferAmount = Integer.parseInt(amount.getText().toString());
        int fee = 200;
        builder.setMessage("Do you confirm the transfer of " + transferAmount + " to " + receiver.getFirstName() + " " + receiver.getLastName() + " using the transfer in 12 method?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(CompleteTransferActivity.this, TransferIn12Activity.class);
                intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
                intent.putExtra("receiverPhoneNumber", receiver.getSimCard().getPhoneNumber());
                intent.putExtra("amount", transferAmount);
                if (transferAmount + 200 <= sender.getCard().getBalance()) {
                    intent.putExtra("confirm", true);
                } else {
                    intent.putExtra("confirm", false);
                }
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        return builder.create();
    }
}