package com.example.boyko.phonebook;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by boyko on 02.12.2017.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {

        this.context = context;
        this.contacts = contacts;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View customView = view;
        if(customView == null)
            view = layoutInflater.inflate(R.layout.list_layout, viewGroup,false);

        Contact itemContact = getContact(i);

        ((TextView)view.findViewById(R.id.contact_name)).setText(itemContact.getFirstName()+" "+itemContact.getSecondName());
        ((TextView)view.findViewById(R.id.contact_number)).setText(itemContact.getPhoneNumber());

        return view;
    }

    public Contact getContact(int position){
        return ((Contact)getItem(position));
    }




    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
