package com.example.boyko.phonebook.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.boyko.phonebook.IntentContainer;
import com.example.boyko.phonebook.Model.Contact;
import com.example.boyko.phonebook.ContactAdapter;
import com.example.boyko.phonebook.R;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ListView contactListView;
    private ArrayList<Contact> contacts;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        initRealm();

        contactListView = (ListView) findViewById(R.id.contactList);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        contacts = initContactList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createNewContactIntent = new Intent(MainActivity.this, EditContact.class);
                startActivityForResult(createNewContactIntent, IntentContainer.REQUESTCODE_NEW_CONTACT);
            }
        });

        ContactAdapter contactAdapter = new ContactAdapter(this, contacts);
        contactListView.setAdapter(contactAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact itemContact = (Contact) contactListView.getAdapter().getItem(i);
                Intent toContactActivityIntent = new Intent(MainActivity.this, ContactActivity.class);
                toContactActivityIntent.putExtra(IntentContainer.CONTACT, itemContact);
                startActivity(toContactActivityIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void initRealm(){
        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }
    public ArrayList<Contact> initContactList() {
        ArrayList<Contact> contacts1 = new ArrayList<Contact>();
        for (int i = 0; i < 10; i++) {
            Contact contact = new Contact(Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i));
            contacts1.add(contact);
        }
        return contacts1;
    }
}
