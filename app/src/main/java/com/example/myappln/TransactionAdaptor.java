package com.example.myappln;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdaptor extends RecyclerView.Adapter<TransactionAdaptor.TransactionViewHolder> {
    private List<Transaction> transactions;
    private TransactionViewClickListener listener;

    public TransactionAdaptor(List<Transaction> transactions, TransactionViewClickListener listener) {
        this.transactions = transactions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.amount.setText(Integer.toString(transaction.getAmount()));
        holder.trackingNumber.setText(Integer.toString(transaction.getTrackingNumber()));
        holder.time.setText(transaction.getTime().toString());
        if (transaction instanceof Transfer) {
            holder.transferType.setText("TRANSFER");
        } else if (transaction instanceof ToppingUp) {
            holder.transferType.setText("TOPPING UP");
        } else if (transaction instanceof ChargeSimCard) {
            holder.transferType.setText("CHARGE SIMCARD");
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView transferType, amount, time, trackingNumber;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transferType = (TextView) itemView.findViewById(R.id.transactionType);
            amount = (TextView) itemView.findViewById(R.id.amounutTransaction);
            time = (TextView) itemView.findViewById(R.id.TransactionTime);
            trackingNumber = (TextView) itemView.findViewById(R.id.transactionTrackingnumber);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClickListener(view, getAdapterPosition());
        }
    }

    public interface TransactionViewClickListener {
        void onClickListener(View v, int position);
    }
}
