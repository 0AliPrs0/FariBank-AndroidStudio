package com.example.myappln;

import android.os.ParcelUuid;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String password;

    public Person(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 11;
    }

    public static boolean checkNationalCode(String nationalCode) {
        return nationalCode.length() == 10;
    }

    public static boolean checkPassword(String password) {
        Pattern pattern1 = Pattern.compile("[a-z]");
        Pattern pattern2 = Pattern.compile("[A-Z]");
        Pattern pattern3 = Pattern.compile("[0-9]");
        Pattern pattern4 = Pattern.compile("[@_#.+*/-]");
        Matcher matcher1 = pattern1.matcher(password);
        Matcher matcher2 = pattern2.matcher(password);
        Matcher matcher3 = pattern3.matcher(password);
        Matcher matcher4 = pattern4.matcher(password);
        return matcher1.find() && matcher2.find() && matcher3.find() && matcher4.find();
    }
}
