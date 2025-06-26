package ir.ac.kntu;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserAccount extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<RecentlyAccountForTransfer> recentlyAccount = new ArrayList<>();
    private final List<Contact> myContacts = new ArrayList<>();
    private final List<Transfer> transfers = new LinkedList<>();
    private int balanceAccount;
    private String accountNumber;
    private RemainingFund remainingFund;
    private List<ProfitFund> profitFunds = new ArrayList<>();
    private List<BonusFund> bonusFunds = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public UserAccount() {
        super();
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String nationalId, String password, int balanceAccount, String accountNumber) {
        super(firstName, lastName, phoneNumber, nationalId, password);
        this.balanceAccount = balanceAccount;
        this.accountNumber = accountNumber;
//        this.cardPassword = -1;
//        this.isActingContact = true;
//        this.simCardValidity = simCardValidity;
//        this.cardNumber = "60379" + phoneNumber;
//        this.isBlocked = false;
        this.remainingFund = new RemainingFund();
    }

    public int getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(int balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<RecentlyAccountForTransfer> getRecentlyAccount() {
        return recentlyAccount;
    }

    public void addRecentlyAccount(RecentlyAccountForTransfer recentAccount) {
        recentlyAccount.add(recentAccount);
    }

    public List<Contact> getMyContacts() {
        return myContacts;
    }

    public void addContact(Contact contact) {
        myContacts.add(contact);
    }

    public void removeContact(Contact contact) {
        myContacts.remove(contact);
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void addTransfer(Transfer transfer) {
        transfers.add(transfer);
    }

    public RemainingFund getRemainingFund() {
        return remainingFund;
    }

    public void setRemainingFund(RemainingFund remainingFund) {
        this.remainingFund = remainingFund;
    }

    public List<ProfitFund> getProfitFunds() {
        return profitFunds;
    }

    public List<BonusFund> getBonusFunds() {
        return bonusFunds;
    }

    public void addProfitFund(ProfitFund profitFund) {
        profitFunds.add(profitFund);
    }

    public void addBonusFund(BonusFund bonusFund) {
        bonusFunds.add(bonusFund);
    }

    public void removeProfitFund(ProfitFund profitFund) {
        profitFunds.remove(profitFund);
    }

    public void removeBonusFund(BonusFund bonusFund) {
        bonusFunds.remove(bonusFund);
    }

    public List<Loan> getLoans(){
        return loans;
    }

    public void addLoans(Loan loan){
        loans.add(loan);
    }
}
