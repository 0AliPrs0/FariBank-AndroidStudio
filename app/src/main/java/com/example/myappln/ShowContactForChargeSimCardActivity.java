package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.ac.kntu.util.Calendar;

public class ShowContactForChargeSimCardActivity extends AppCompatActivity {
    private Customer sender;
    private Contact receiver;
    private RecyclerView recyclerView;
    private List<Contact> contacts;
    private ContactAdaptor.ContactViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_contact_for_charge_sim_card);
        extractAll();
        setAdaptor();
    }

    private void extractAll() {
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        recyclerView = findViewById(R.id.contacts2);
        contacts = sender.getContacts();
    }

    private void setAdaptor() {
        setOnClick();
        ContactAdaptor adaptor = new ContactAdaptor(contacts, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    private void setOnClick() {
        listener = new ContactAdaptor.ContactViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                receiver = contacts.get(position);
                Intent intent = new Intent(ShowContactForChargeSimCardActivity.this, CompeleteChargeSimcardActivity.class);
                intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
                intent.putExtra("receiverPhoneNumber", receiver.getPerson().getSimCard().getPhoneNumber());
                startActivity(intent);
            }
        };
    }
}