package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    private Customer customer;
    private List<Contact> contacts = new ArrayList<>();
    private RecyclerView recyclerView;
    private String phoneNumber;
    private TextView empty;
    private ImageView addContact, home;
    private ContactAdaptor.ContactViewClickListener listener;
    private ContactAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        customer = MainActivity.getMainBank().findCustomerWithPhoneNumber(phoneNumber);
        contacts = customer.getContacts();
        int removedPosition = getIntent().getIntExtra("deletedPosition", -1);
        recyclerView = findViewById(R.id.ContactsList);
        addContact = findViewById(R.id.addContact);
        setAdaptor();
        home = findViewById(R.id.home2);
        empty = findViewById(R.id.emptyContact);
        if (contacts.isEmpty()) {
            empty.setText("You have no contact");
        }
        home.setOnClickListener(view -> {
            Intent intent = new Intent(ContactActivity.this, DashboardActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
        addContact.setOnClickListener(view -> {
            Intent intent = new Intent(ContactActivity.this, AddToContactActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            startActivity(intent);
        });
    }

    private void setAdaptor() {
        setOnClickListener();
        adaptor = new ContactAdaptor(contacts, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    public void setOnClickListener() {
        listener = new ContactAdaptor.ContactViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), ShowContactActivity.class);
                intent.putExtra("customerPhoneNumber", customer.getSimCard().getPhoneNumber());
                intent.putExtra("savedFirstName", contacts.get(position).getSavedFirstName());
                intent.putExtra("savedLastName", contacts.get(position).getSavedLastName());
                intent.putExtra("savedCustomer", contacts.get(position).getPerson().getSimCard().getPhoneNumber());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        };
    }
}