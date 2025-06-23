package com.example.myappln;

import android.widget.Toast;

public class SignupThread implements Runnable {
    private Customer customer;

    public SignupThread(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            MainActivity.getMainBank().addToRegisters(customer);
        } catch (InterruptedException e) {
            System.out.println("Signup thread error");
        }
    }
}
