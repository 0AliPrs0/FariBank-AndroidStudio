package ir.ac.kntu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<UserAccount> userAccounts = new ArrayList<>();
    private List<Requests> requests = new ArrayList<>();
    private List<Transfer> transfers = new ArrayList<>();
    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void addUserAccount(UserAccount userAccount) {
        userAccounts.add(userAccount);
    }

    public List<Requests> getRequest() {
        return requests;
    }

    public void addRequest(Requests newRequest) {
        requests.add(newRequest);
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void addTransfer(Transfer transfer){
        transfers.add(transfer);
    }

    public void removeTransfer(Transfer transfer){
        transfers.remove(transfer);
    }

    public UserAccount findUserWithPhoneNumber(String phoneNumber) {
        for (UserAccount user : userAccounts) {
            if (phoneNumber.equals(user.getPhoneNumber())) {
                return user;
            }
        }
        return null;
    }

    public UserAccount findUserWithAccountNumber(String accountNumber) {
        for (UserAccount user : userAccounts) {
            if (accountNumber.equals(user.getAccountNumber())) {
                return user;
            }
        }
        return null;
    }
}
