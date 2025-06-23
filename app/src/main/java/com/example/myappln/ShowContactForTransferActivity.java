package com.example.myappln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowContactForTransferActivity extends AppCompatActivity {
    private Customer sender;
    private RecyclerView recyclerView;
    private List<Contact> contacts;
    private ContactAdaptor.ContactViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact_for_transfer);
        recyclerView = findViewById(R.id.contacts);
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        contacts = sender.getContacts();
        setAdaptor();
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
                Contact receiver = contacts.get(position);
                Intent intent = new Intent(getApplicationContext(), CompleteTransferActivity.class);
                intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
                intent.putExtra("receiverPhoneNumber", receiver.getPerson().getSimCard().getPhoneNumber());
                startActivity(intent);
            }
        };
    }
}