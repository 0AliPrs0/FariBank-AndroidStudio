package com.example.myappln;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestAdaptor extends RecyclerView.Adapter<RequestAdaptor.RequestViewHolder> {
    private List<Request> requests;

    public RequestAdaptor(List<Request> requests) {
        this.requests = requests;
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        private TextView text, answer, partOfBank, status;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView68);
            answer = itemView.findViewById(R.id.textView69);
            status = itemView.findViewById(R.id.textView67);
            partOfBank = itemView.findViewById(R.id.textView66);
        }
    }

    @NonNull
    @Override
    public RequestAdaptor.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new RequestAdaptor.RequestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdaptor.RequestViewHolder holder, int position) {
        Request request = requests.get(position);
        holder.text.setText(request.getRequestText());
        holder.answer.setText(request.getAnswer());
        holder.partOfBank.setText(request.getPart().toString());
        holder.status.setText(request.getStatus().toString());
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
