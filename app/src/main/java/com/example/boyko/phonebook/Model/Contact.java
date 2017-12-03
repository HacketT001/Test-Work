package com.example.boyko.phonebook.Model;

import java.io.Serializable;


public class Contact implements Serializable {
    private String phoneNumber;
    private String firstName;
    private String secondName;
    private String email;
    private String secondPhone;

    public Contact(){

    }

    public Contact(String phoneNumber, String firstName, String secondName, String email, String secondPhone) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.secondPhone = secondPhone;
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
}
