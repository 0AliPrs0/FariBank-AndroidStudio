package com.example.myappln;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin extends Person {
    private Map<PartOfNeoBank, Boolean> authorizedParts = new HashMap<>();

    public Admin(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, password);
        authorizedParts.put(PartOfNeoBank.AUTHENTICATION, false);
        authorizedParts.put(PartOfNeoBank.TRANSFER, false);
        authorizedParts.put(PartOfNeoBank.SUPPORT, false);
        authorizedParts.put(PartOfNeoBank.CONTACT, false);
        authorizedParts.put(PartOfNeoBank.SETTING, false);
    }

}