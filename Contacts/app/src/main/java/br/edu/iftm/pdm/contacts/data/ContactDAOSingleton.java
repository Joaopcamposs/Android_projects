package br.edu.iftm.pdm.contacts.data;

import java.util.ArrayList;
import java.util.Collections;

import br.edu.iftm.pdm.contacts.model.Contact;

public class ContactDAOSingleton {
    private static ContactDAOSingleton INSTANCE;
    private ArrayList<Contact> contacts;

    private ContactDAOSingleton() {
        this.contacts = new ArrayList<>();
        DummyData.generateContacts(this.contacts);
        Collections.sort(this.contacts);
    }

    public static final ContactDAOSingleton getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new ContactDAOSingleton();
        }
        return INSTANCE;
    }

    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }
}
