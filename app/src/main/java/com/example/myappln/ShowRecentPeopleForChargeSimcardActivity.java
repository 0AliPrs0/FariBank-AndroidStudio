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

public class ShowRecentPeopleForChargeSimcardActivity extends AppCompatActivity {
    private Customer sender, receiver;
    private List<Customer> recentPeople;
    private RecyclerView recyclerView;
    private CustomerAdaptor.CustomerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_recent_people_for_charge_simcard);
        extractAll();
        setAdaptor();
    }

    private void extractAll() {
        sender = MainActivity.getMainBank().findCustomerWithPhoneNumber(getIntent().getStringExtra("senderPhoneNumber"));
        recentPeople = sender.getRecentPeople();
        recyclerView = findViewById(R.id.contacts2);
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
                receiver = sender.getRecentPeople().get(position);
                Intent intent = new Intent(ShowRecentPeopleForChargeSimcardActivity.this, CompeleteChargeSimcardActivity.class);
                intent.putExtra("senderPhoneNumber", sender.getSimCard().getPhoneNumber());
                intent.putExtra("receiverPhoneNumber", receiver.getSimCard().getPhoneNumber());
                startActivity(intent);
            }
        };
    }
}