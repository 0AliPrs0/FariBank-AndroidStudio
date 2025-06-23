package com.example.myappln;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.ac.kntu.util.Calendar;

public class Customer extends Person {
    private SimCard simCard;
    private String nationalCode;
    private Card card;
    private List<Customer> recentPeople;
    private List<Contact> contacts;
    private Boolean hasRemaining = false;
    private List<Request> requests;


    public Customer(String firstName, String lastName, String phoneNumber, String nationalCode, String password) {
        super(firstName, lastName, password);
        this.simCard = new SimCard(phoneNumber, 0);
        this.nationalCode = nationalCode;
        contacts = new ArrayList<>();
        recentPeople = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public SimCard getSimCard() {
        return simCard;
    }

    public void setSimCard(SimCard simCard) {
        this.simCard = simCard;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Customer> getRecentPeople() {
        return recentPeople;
    }

    public void setRecentPeople(List<Customer> recentPeople) {
        this.recentPeople = recentPeople;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Boolean getHasRemaining() {
        return hasRemaining;
    }

    public void setHasRemaining(Boolean hasRemaining) {
        this.hasRemaining = hasRemaining;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            return this.getNationalCode().equals(customer.getNationalCode()) && this.getSimCard().getPhoneNumber().equals(customer.getSimCard().getPhoneNumber()) && this.getFirstName().equals(customer.getFirstName()) && this.getLastName().equals(customer.getLastName());
        }
        return false;
    }

    @Override
    public String toString() {
        return "First name: " + getFirstName() + "\n"
                + "Last name: " + getLastName() + "\n"
                + "Phone number: " + getSimCard().getPhoneNumber() + "\n"
                + "National code: " + getNationalCode();
    }

    public boolean haveThisContact(String phoneNumber) {
        for (Contact contact : contacts) {
            if (phoneNumber.equals(contact.getPerson().getSimCard().getPhoneNumber())) {
                return true;
            }
        }
        return false;
    }

    public boolean canGetLoan() {
        int numberOfFailedLoan = 0;
        Instant now = Calendar.now();
        List<Loan> loans = MainActivity.getMainBank().getAllLoans().getOrDefault(this, new ArrayList<Loan>());
        if (loans.isEmpty()) {
            return true;
        }
        for (Loan loan : loans) {
            if (loan.getStatus() == LoanStatus.FAILED) {
                numberOfFailedLoan++;
            }
        }
        if (numberOfFailedLoan >= 3) {
            return false;
        }
        for (Loan loan : loans) {
            if (loan.getStatus() == LoanStatus.ACCEPTED) {
                if (Calendar.daysBetween(now, loan.getNextPayment()) < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
