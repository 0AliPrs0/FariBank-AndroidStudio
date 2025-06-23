package com.example.myappln;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddRequestActivity extends AppCompatActivity {
    private Customer customer;
    private Spinner spinner;
    private PartOfNeoBank[] partOfBanks = {PartOfNeoBank.AUTHENTICATION, PartOfNeoBank.TRANSFER, PartOfNeoBank.CONTACT, PartOfNeoBank.SETTING, PartOfNeoBank.SUPPORT};
    private PartOfNeoBank wantedPart = null;
    private TextView error;
    private Button confirm;
    private EditText requestText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_request);
        extractAll();
        setSpinner();
        confirm.setOnClickListener(view -> {
            if (requestText.getText().toString().isEmpty()) {
                error.setText("Please write your request");
                return;
            }
            error.setText("");
            AlertDialog alertDialog = createDialog();
            alertDialog.show();
        });
    }

    private void extractAll() {
        spinner = findViewById(R.id.spinner);
        error = findViewById(R.id.error12);
        confirm = findViewById(R.id.button2);
        requestText = findViewById(R.id.editTextText2);
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("phoneNumber"));
    }

    public void setSpinner() {
        ArrayAdapter<PartOfNeoBank> adapter = new ArrayAdapter<PartOfNeoBank>(AddRequestActivity.this, android.R.layout.simple_spinner_item, partOfBanks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wantedPart = (PartOfNeoBank) parent.getItemAtPosition(position);
                Toast.makeText(AddRequestActivity.this, wantedPart.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                wantedPart = PartOfNeoBank.AUTHENTICATION;
            }
        });
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure about registering a request for " + wantedPart.toString() + " section?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Request request = new Request(wantedPart, RequestStatus.RECORDED, requestText.getText().toString(), "Our colleagues will contact you soon");
                customer.getRequests().add(request);
                Intent intent = new Intent(AddRequestActivity.this, RequestActivity.class);
                intent.putExtra("phoneNumber", customer.getSimCard().getPhoneNumber());
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