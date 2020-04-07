package com.example.sharingapp;

import android.content.Context;

import java.util.ArrayList;

public class ContactListController {
    private ContactList contactList;

    public ContactListController(ContactList contactList) {
        this.contactList = contactList;
    }

    public void setContacts(ArrayList<Contact> contact_list) {
        contactList.setContacts(contact_list);

    }

    public ArrayList<Contact> getContacts() {
        return contactList.getContacts();
    }

    public ArrayList<String> getAllUsernames() {
        return contactList.getAllUsernames();
    }

    public void addContact(Contact contact) {
        contactList.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactList.deleteContact(contact);
    }

    public Contact getContact(int pos) {
        return contactList.getContact(pos);
    }

    public int getSize() {
        return contactList.getSize();
    }

    public Contact getContactByUsername(String username) {
        return contactList.getContactByUsername(username);
    }

    public boolean hasContact(Contact contact) {
        return contactList.hasContact(contact);
    }

    public int getIndex(Contact contact) {
        return contactList.getIndex(contact);
    }

    public boolean isUsernameAvailable(String username) {
        return contactList.isUsernameAvailable(username);
    }

    public void loadContacts(Context context) {
        contactList.loadContacts(context);
    }

    public void saveContacts(Context context) {
        contactList.saveContacts(context);
    }

    public boolean addNewContact(Contact contact, Context context) {
        AddContactCommand add_contact_cmd = new AddContactCommand(contactList, contact, context);
        add_contact_cmd.execute();
        return add_contact_cmd.isExecuted();
    }

    public boolean editContact(Contact old, Contact updated, Context context) {
        EditContactCommand edit_contact_cmd = new EditContactCommand(contactList, updated, old, context);
        edit_contact_cmd.execute();
        return edit_contact_cmd.isExecuted();
    }

    public boolean deleteContact(Contact contact, Context context) {
        DeleteContactCommand del_contact_cmd = new DeleteContactCommand(contactList, contact, context);
        del_contact_cmd.execute();
        return del_contact_cmd.isExecuted();
    }


    public void addObserver(Observer observer) {
        contactList.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        contactList.removeObserver(observer);
    }

}
