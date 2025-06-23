package com.example.myappln;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person {
    private List<Manager> fatherManagers;

    public Manager(String firstName, String lastName, String password, List<Manager> fatherManagers) {
        super(firstName, lastName, password);
        this.fatherManagers = fatherManagers;
    }
}