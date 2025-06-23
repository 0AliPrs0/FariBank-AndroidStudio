package com.example.myappln;

import java.util.List;

public class Contact {
    private String savedFirstName;
    private String savedLastName;
    private Customer person;

    public Contact(String savedFirstName, String savedLastName, Customer person) {
        this.savedFirstName = savedFirstName;
        this.savedLastName = savedLastName;
        this.person = person;
    }

    public String getSavedFirstName() {
        return savedFirstName;
    }

    public void setSavedFirstName(String savedFirstName) {
        this.savedFirstName = savedFirstName;
    }

    public String getSavedLastName() {
        return savedLastName;
    }

    public void setSavedLastName(String savedLastName) {
        this.savedLastName = savedLastName;
    }

    public Customer getPerson() {
        return person;
    }

    public void setPerson(Customer person) {
        this.person = person;
    }
}
