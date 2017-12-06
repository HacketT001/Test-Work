package com.example.boyko.phonebook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.boyko.phonebook.IntentContainer;
import com.example.boyko.phonebook.adapters.ContactRealmAdapter;
import com.example.boyko.phonebook.models.Contact;
import com.example.boyko.phonebook.adapters.ContactAdapter;
import com.example.boyko.phonebook.R;
import com.example.boyko.phonebook.models.RealmContact;

import java.util.Arrays;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

//import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ListView contactListView;
    private LinkedList<Contact> contacts;

    private String contactID;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        initRealm();

        contactListView = (ListView) findViewById(R.id.contactList);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent createNewContactIntent = new Intent(MainActivity.this, EditContact.class);
                startActivityForResult(createNewContactIntent, IntentContainer.REQUESTCODE_NEW_CONTACT);
            }
        });

        ContactRealmAdapter contactAdapter = new ContactRealmAdapter(realm.where(RealmContact.class).findAll());
        contactListView.setAdapter(contactAdapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RealmContact itemContact = (RealmContact) contactListView.getAdapter().getItem(i);
                Intent toContactActivityIntent = new Intent(MainActivity.this, ContactActivity.class);
                toContactActivityIntent.putExtra(IntentContainer.CONTACT, itemContact.getSimpleContact());
                startActivityForResult(toContactActivityIntent, IntentContainer.REQUESTCODE_EDIT_CONTACT);
                contactID = itemContact.getId();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        realm.beginTransaction();
        if (requestCode == IntentContainer.REQUESTCODE_EDIT_CONTACT) {
            if (resultCode == RESULT_OK) {
                Contact updatedContact = (Contact) data.getSerializableExtra(IntentContainer.CONTACT);
                realm.where(RealmContact.class).equalTo("id", contactID).findFirst().updateRealmContactBySimpleContact(updatedContact);
            } else if (requestCode == RESULT_CANCELED) {
                realm.where(RealmContact.class).equalTo("id", contactID).findFirst().deleteFromRealm();
            }

        } else if (requestCode == IntentContainer.REQUESTCODE_NEW_CONTACT)
            if (resultCode == RESULT_OK) {
                realm.copyToRealm(new RealmContact((Contact) data.getSerializableExtra(IntentContainer.CONTACT)));
            }
        realm.commitTransaction();
    }

    private void initRealm() {

        Realm.init(this);
        realm = Realm.getDefaultInstance();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("tryforth.realm")
                .schemaVersion(3)
                .build();
        realm.setDefaultConfiguration(config);

    }
}
