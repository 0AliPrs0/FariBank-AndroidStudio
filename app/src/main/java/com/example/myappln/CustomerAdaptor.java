package com.example.myappln;

import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerAdaptor.CustomerViewHolder> {
    private List<Customer> customers;
    private CustomerViewClickListener listener;

    public CustomerAdaptor(List<Customer> customers, CustomerViewClickListener listener) {
        this.customers = customers;
        this.listener = listener;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView fullName;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickListener(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public CustomerAdaptor.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false);
        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdaptor.CustomerViewHolder holder, int position) {
        Customer customer = customers.get(position);
        holder.fullName.setText(customer.getFirstName() + " " + customer.getLastName());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public interface CustomerViewClickListener {
        void onClickListener(View v, int position);
    }
}
