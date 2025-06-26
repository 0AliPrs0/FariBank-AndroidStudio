package ir.ac.kntu;

import java.io.Serializable;

public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int amountTransfer;
    private UserAccount beginningAccount;
    private UserAccount destinAccount;

    public Transfer() {
    }

    public Transfer(int amountTransfer, UserAccount beginningAccount, UserAccount destinAccount) {
        this.amountTransfer = amountTransfer;
        this.beginningAccount = beginningAccount;
        this.destinAccount = destinAccount;
    }

    public int getAmountTransfer() {
        return amountTransfer;
    }

    public void setAmountTransfer(int amountTransfer) {
        this.amountTransfer = amountTransfer;
    }

    public UserAccount getBeginningAccount() {
        return beginningAccount;
    }

    public void setBeginningAccount(UserAccount beginningAccount) {
        this.beginningAccount = beginningAccount;
    }

    public UserAccount getDestinationAccount() {
        return destinAccount;
    }

    public void setDestinationAccount(UserAccount destinAccount) {
        this.destinAccount = destinAccount;
    }

    @Override
    public String toString() {
        return  "amount Transfer=" + amountTransfer +
                ", destin account='" + destinAccount.getLastName() + '\'';
    }
}
