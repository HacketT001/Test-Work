package com.example.boyko.phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ListView contactListView;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);


        contactListView = (ListView)findViewById(R.id.contactList);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        contacts = initContactList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       ContactAdapter contactAdapter = new ContactAdapter(this, contacts);
       contactListView.setAdapter(contactAdapter);

    }
    public ArrayList<Contact> initContactList(){
       ArrayList<Contact> contacts1 = new ArrayList<Contact>();
        for(int i = 0; i< 5; i++) {
            Contact contact = new Contact(Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i));
            contacts1.add(contact);
        }
        return contacts1;
    }
}
