package com.example.boyko.phonebook;

/**
 * Created by boyko on 03.12.2017.
 */

public interface IntentContainer {
    String RULE = "RULE";
    String CONTACT = "CONTACT";

    int RULE_CREATE = 1;
    int RULE_CHANGE = 0;

    int REQUESTCODE_NEW_CONTACT = 1;
    int REQUESTCODE_EDIT_CONTACT = 0;
}
