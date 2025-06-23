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

public class ShowRecentPeopleForTransferActivity extends AppCompatActivity {
    private Customer sender;
    private List<Customer> recentPeople;
    private RecyclerView recyclerView;
    private CustomerAdaptor.CustomerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recent_people_for_transfer);
        recyclerView = findViewById(R.id.recentPeople);
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        recentPeople = sender.getRecentPeople();
        setAdaptor();
    }

    private void setAdaptor() {
        setOnClick();
        CustomerAdaptor adaptor = new CustomerAdaptor(recentPeople, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    private void setOnClick() {
        listener = new CustomerAdaptor.CustomerViewClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Customer receiver = recentPeople.get(position);
                Intent intent = new Intent(getApplicationContext(), CompleteTransferActivity.class);
                intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
                intent.putExtra("receiverPhoneNumber", receiver.getSimCard().getPhoneNumber());
                startActivity(intent);
            }
        };
    }
}