package ir.ac.kntu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.R;

import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private List<Contact> contactList;
    private ImageView home;
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        UserAccount user = MainActivity.getMainBank().findUserWithPhoneNumber(phoneNumber);

        home = findViewById(R.id.home);
        home.setOnClickListener(v -> {

            Intent intent = new Intent(ContactActivity.this, DashBoardActivity.class);
            intent.putExtra("phoneNumber", user.getPhoneNumber());
            startActivity(intent);
        });

        contactList = user.getMyContacts();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactsAdapter(contactList, new ContactsAdapter.OnContactClickListener() {
            @Override
            public void onEditClick(int position) {
                editContact(position);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteContact(position);
            }
        });
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addContactButton).setOnClickListener(v -> addNewContact());
    }

    private void addNewContact() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_contact, null);
        final EditText firstNameInput = dialogView.findViewById(R.id.firstNameInput);
        final EditText lastNameInput = dialogView.findViewById(R.id.lastNameInput);
        final EditText phoneNumberInput = dialogView.findViewById(R.id.phoneNumberInput);

        new AlertDialog.Builder(this)
                .setTitle("Add new contact")
                .setView(dialogView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String firstName = firstNameInput.getText().toString();
                        String lastName = lastNameInput.getText().toString();
                        String phoneNumber = phoneNumberInput.getText().toString();

                        contactList.add(new Contact(firstName, lastName, phoneNumber));
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void editContact(final int position) {
        Contact contact = contactList.get(position);

        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_contact, null);
        final EditText firstNameInput = dialogView.findViewById(R.id.firstNameInput);
        final EditText lastNameInput = dialogView.findViewById(R.id.lastNameInput);
        final EditText phoneNumberInput = dialogView.findViewById(R.id.phoneNumberInput);

        firstNameInput.setText(contact.getFirstName());
        lastNameInput.setText(contact.getLName());
        phoneNumberInput.setText(contact.getPhoneNumber());

        new AlertDialog.Builder(this)
                .setTitle("Edit contact")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    contactList.get(position).setFirstName(firstNameInput.getText().toString());
                    contactList.get(position).setLName(lastNameInput.getText().toString());
                    contactList.get(position).setPhoneNumber(phoneNumberInput.getText().toString());
                    adapter.notifyItemChanged(position);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteContact(int position) {
        contactList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}

