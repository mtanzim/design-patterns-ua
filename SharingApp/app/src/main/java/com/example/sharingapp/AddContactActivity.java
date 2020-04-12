package com.example.sharingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Add a new contact
 */
public class AddContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private ContactListController contactListController = new ContactListController(contact_list);
    private Context context;

    private EditText username;
    private EditText email;

    private String username_str = username.getText().toString();
    private String email_str = email.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        username_str = username.getText().toString();
        email_str = email.getText().toString();

        context = getApplicationContext();
        contactListController.loadContacts(context);
    }

    private boolean validateInput() {
        if (username_str.equals("")) {
            username.setError("Empty field!");
            return false;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return false;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return false;
        }
        return true;
    }


    public void saveContact(View view) {
        if (!validateInput()) {
            return;
        }

        if (!contactListController.isUsernameAvailable(username_str)) {
            username.setError("Username already taken!");
            return;
        }

        Contact contact = new Contact(username_str, email_str, null);
        boolean success = contactListController.addNewContact(contact, context);
        if (!success) {
            return;
        }

        // End AddContactActivity
        finish();
    }
}
