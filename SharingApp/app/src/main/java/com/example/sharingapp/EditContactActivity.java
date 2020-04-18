package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Editing a pre-existing contact consists of deleting the old contact and
 * adding a new contact with the old contact's id. Note: You will not be able
 * contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity implements Observer {

    private ContactList contact_list = new ContactList();
    private ContactListController contactListController = new ContactListController(contact_list);
    private Contact contact;
    private ContactController contactController;
    private EditText email;
    private EditText username;
    private Context context;

    private String email_str;
    private String username_str;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();
        contact_list_controller.addObserver(this);
        contactListController.loadContacts(context);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        contact = contactListController.getContact(pos);
        contactController = new ContactController(contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        email_str = email.getText().toString();
        username_str = username.getText().toString();
        id = contact.getId(); // Reuse the contact id

        username.setText(contact.getUsername());
        email.setText(contact.getEmail());
    }

    private boolean validateInput() {


        if (email_str.equals("")) {
            email.setError("Empty field!");
            return false;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return false;
        }

        String username_str = username.getText().toString();
        String id = contact_controller.getId(); // Reuse the contact id

        // Check that username is unique AND username is changed (Note: if username was
        // not changed
        // then this should be fine, because it was already unique.)
        if (!contactListController.isUsernameAvailable(username_str)
                && !(contactController.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return false;
        }
        return true;


    }

    public void saveContact(View view) {

        if (!validateInput()) {
            return;
        }

        Contact updated_contact = new Contact(username_str, email_str, id);
        boolean success = contactListController.editContact(contact, updated_contact, context);
        if (!success) {
            return;
        }

        // End EditContactActivity
        finish();
    }

    public void deleteContact(View view) {
        boolean success = contactListController.deleteContact(contact, context);
        if (!success) {
            return;
        }

        // End EditContactActivity
        finish();
    }

    /**
     * Called when the activity is destroyed, thus we remove this activity as a
     * listener
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        contact_list_controller.removeObserver(this);
    }

    /**
     * Only need to update the view from the onCreate method
     */
    public void update() {

        if (on_create_update) {

            contact = contact_list_controller.getContact(pos);
            contact_controller = new ContactController(contact);

            username = (EditText) findViewById(R.id.username);
            email = (EditText) findViewById(R.id.email);

            // Update the view
            username.setText(contact_controller.getUsername());
            email.setText(contact_controller.getEmail());
        }
    }

}
