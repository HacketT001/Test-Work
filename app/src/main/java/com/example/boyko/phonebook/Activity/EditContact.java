package com.example.boyko.phonebook.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boyko.phonebook.IntentContainer;
import com.example.boyko.phonebook.Model.Contact;
import com.example.boyko.phonebook.R;


public class EditContact extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextSecondName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextSecondPhone;

    private ImageView imageViewHeader;
    private TextView textViewHeader;

    private Contact contact;

    private Intent thisIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact_activity);

        editTextEmail = (EditText) findViewById(R.id.edit_contact_activity_et_email);
        editTextFirstName = (EditText) findViewById(R.id.edit_contact_activity_et_firstname);
        editTextSecondName = (EditText) findViewById(R.id.edit_contact_activity_et_secondname);
        editTextPhone = (EditText) findViewById(R.id.edit_contact_activity_et_firstphone);
        editTextSecondPhone = (EditText) findViewById(R.id.edit_contact_activity_et_secondphone);

        imageViewHeader = (ImageView) findViewById(R.id.edit_contact_activity_image);
        textViewHeader = (TextView) findViewById(R.id.edit_contact_activity_header_et);

        thisIntent = getIntent();
        if (thisIntent.getIntExtra(IntentContainer.RULE, 1) == IntentContainer.RULE_CHANGE) {

            contact = (Contact) thisIntent.getSerializableExtra(IntentContainer.CONTACT);
            textViewHeader.setText(R.string.edit_contact_header_et_edit);
            Drawable imageForChange = getResources().getDrawable(R.drawable.ic_create_white_24dp);
            imageViewHeader.setImageDrawable(imageForChange);
            initEditTexts();

        } else {
            contact = new Contact();
        }
    }

    public void initEditTexts() {
        editTextPhone.setText(contact.getPhoneNumber());
        editTextFirstName.setText(contact.getFirstName());

        if (contact.getSecondName() != null)
            editTextSecondName.setText(contact.getSecondName());

        if (contact.getSecondPhone() != null)
            editTextSecondPhone.setText(contact.getSecondPhone());

        if (contact.getEmail() != null)
            editTextEmail.setText(contact.getEmail());
    }

}
