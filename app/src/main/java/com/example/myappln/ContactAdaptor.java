package com.example.myappln;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdaptor extends RecyclerView.Adapter<ContactAdaptor.ContactViewHolder> {
    private List<Contact> contacts;
    private ContactViewClickListener listener;

    public ContactAdaptor(List<Contact> contacts, ContactViewClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView fullName;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.contactFullName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ContactAdaptor.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdaptor.ContactViewHolder holder, int position) {
        holder.fullName.setText(contacts.get(position).getSavedFirstName() + " " + contacts.get(position).getSavedLastName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface ContactViewClickListener {
        void onClick(View view, int position);
    }
}
