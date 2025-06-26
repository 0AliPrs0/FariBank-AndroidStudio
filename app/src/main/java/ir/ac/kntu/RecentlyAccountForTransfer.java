package ir.ac.kntu;

import java.io.Serializable;

public class RecentlyAccountForTransfer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int accountNumber;

    public RecentlyAccountForTransfer() {
    }

    public RecentlyAccountForTransfer(String name, int accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", accountNumber=" + accountNumber;
    }
}
