package com.example.myappln;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FundAdaptor extends RecyclerView.Adapter<FundAdaptor.FundViewHolder> {
    private List<Fund> funds;
    private FundViewClickListener listener;

    public FundAdaptor(List<Fund> funds, FundViewClickListener listener) {
        this.funds = funds;
        this.listener = listener;
    }

    public class FundViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView type, name, balance;

        public FundViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.textView72);
            name = itemView.findViewById(R.id.textView73);
            balance = itemView.findViewById(R.id.textView74);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public FundAdaptor.FundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_item, parent, false);
        return new FundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FundAdaptor.FundViewHolder holder, int position) {
        Fund fund = funds.get(position);
        holder.name.setText(fund.getName());
        holder.balance.setText(Integer.toString(fund.getBalance()));
        if (fund instanceof BonusFund) {
            holder.type.setText("BonusFund");
        } else {
            holder.type.setText("SavingFund");
        }
    }

    @Override
    public int getItemCount() {
        return funds.size();
    }

    public interface FundViewClickListener {
        void onClick(View view, int position);
    }
}
