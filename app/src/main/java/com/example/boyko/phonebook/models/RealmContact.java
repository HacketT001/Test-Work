package com.example.boyko.phonebook.models;

import com.example.boyko.phonebook.models.Contact;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by boyko on 05.12.2017.
 */

public class RealmContact extends RealmObject {

  //  @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private String phoneNumber;
    private String firstName;
    private String secondName;
    private String email;
    private String secondPhone;

    public RealmContact(){

    }

    public RealmContact(Contact contact) {
        phoneNumber = contact.getPhoneNumber();
        firstName = contact.getFirstName();
        secondName = contact.getSecondName();
        email = contact.getEmail();
        secondPhone = contact.getSecondPhone();
    }

    public RealmContact(String phoneNumber, String firstName, String secondName, String email, String secondPhone) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.secondPhone = secondPhone;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public Contact getSimpleContact(){
        return new Contact (phoneNumber, firstName, secondName, email, secondPhone, id);
    }

    public void updateRealmContactBySimpleContact(Contact contact){
        phoneNumber = contact.getPhoneNumber();
        firstName = contact.getFirstName();
        secondName = contact.getSecondName();
        email = contact.getEmail();
        secondPhone = contact.getSecondPhone();
    }
}
