package com.example.boyko.phonebook.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.boyko.phonebook.R;
import com.example.boyko.phonebook.models.RealmContact;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by boyko on 06.12.2017.
 */

public class ContactRealmAdapter  extends RealmBaseAdapter<RealmContact> implements ListAdapter {

    private static class ViewHolder{
        TextView name;
        TextView number;
    }

    public ContactRealmAdapter(@Nullable OrderedRealmCollection<RealmContact> data) {
        super(data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)view.findViewById(R.id.contact_name);
            viewHolder.number = (TextView)view.findViewById(R.id.contact_number);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        if (adapterData != null){
            RealmContact contact =adapterData.get(i);
            viewHolder.name.setText(contact.getFirstName()+" "+contact.getSecondName());
            viewHolder.number.setText(contact.getPhoneNumber());
        }
        return view;
    }

    @Override
    public RealmContact getItem(int i) {
        return adapterData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
