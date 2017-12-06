package com.example.boyko.phonebook.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boyko.phonebook.IntentContainer;
import com.example.boyko.phonebook.models.Contact;
import com.example.boyko.phonebook.R;

public class ContactActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewFirstPhone;
    private TextView textViewEmail;
    private TextView textViewSecondPhone;

    private TextView textViewSecondPhoneCaption;
    private TextView textViewEmailCaption;

    private ImageView imageViewDelete;
    private ImageView imageViewChange;

    private Contact thisContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);
        thisContact = (Contact) getIntent().getSerializableExtra(IntentContainer.CONTACT);

        textViewName = (TextView) findViewById(R.id.contact_activity_name);
        textViewFirstPhone = (TextView) findViewById(R.id.contact_activity_phone);
        textViewEmail = (TextView) findViewById(R.id.contact_activity_email);
        textViewSecondPhone = (TextView) findViewById(R.id.contact_activity_seconphone);

        imageViewChange = (ImageView) findViewById(R.id.contact_activity_change);
        imageViewDelete = (ImageView) findViewById(R.id.contact_activity_delete);

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setTitle("Delete")
                        .setMessage("Do you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                setResult(RESULT_CANCELED);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        imageViewChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toContactActivityIntent = new Intent(ContactActivity.this, EditContact.class);
                toContactActivityIntent.putExtra(IntentContainer.CONTACT, thisContact);
                toContactActivityIntent.putExtra(IntentContainer.RULE, IntentContainer.RULE_CHANGE);
                startActivityForResult(toContactActivityIntent, IntentContainer.RULE_CHANGE);

            }
        });
        updateContact();
    }

    private void updateContact() {
        textViewName.setText(thisContact.getFirstName());
        textViewFirstPhone.setText(thisContact.getPhoneNumber());
        textViewName.append(" " + thisContact.getSecondName());
        textViewSecondPhone.setText(thisContact.getSecondPhone());
        textViewEmail.setText(thisContact.getEmail());
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent finishIntent = new Intent();
        finishIntent.putExtra(IntentContainer.CONTACT, thisContact);
        setResult(RESULT_OK, finishIntent);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            thisContact = (Contact) data.getSerializableExtra(IntentContainer.CONTACT);
            updateContact();
        }
    }
}
