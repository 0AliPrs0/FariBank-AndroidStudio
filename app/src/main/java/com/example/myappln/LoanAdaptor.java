package com.example.myappln;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LoanAdaptor extends RecyclerView.Adapter<LoanAdaptor.LoanViewHolder> {
    private List<Loan> loans;
    private LoanViewClickListener listener;

    public LoanAdaptor(List<Loan> loans, LoanViewClickListener listener) {
        this.loans = loans;
        this.listener = listener;
    }

    public class LoanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, amount, status;

        public LoanViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.loanName);
            amount = itemView.findViewById(R.id.loanAmount);
            status = itemView.findViewById(R.id.loanStatus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public LoanAdaptor.LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_item, parent, false);
        return new LoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanAdaptor.LoanViewHolder holder, int position) {
        Loan loan = loans.get(position);
        holder.name.setText(loan.getName());
        holder.amount.setText(Integer.toString(loan.getAmount()));
        holder.status.setText(loan.getStatus().toString());
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }

    public interface LoanViewClickListener {
        void onClick(View view, int position);
    }
}
