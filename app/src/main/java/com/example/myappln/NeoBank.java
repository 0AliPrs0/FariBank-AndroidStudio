package com.example.myappln;

import java.util.*;

public class NeoBank {
    private List<Customer> notRegisters = new ArrayList<>();
    private List<Customer> registers = new ArrayList<>();
    private List<Customer> otherBanks = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private Map<Customer, ArrayList<Transaction>> transactions = new HashMap<>();
    private List<Transfer> pendingTransfers = new ArrayList<>();
    private List<Integer> trackingNumbers = new ArrayList<>();
    private List<SimCard> allSimCards = new ArrayList<>();
    private Map<Customer, ArrayList<Fund>> allFunds = new HashMap<>();
    private Map<Customer, ArrayList<Loan>> allLoans = new HashMap<>();

    public List<Customer> getNotRegisters() {
        return notRegisters;
    }

    public void setNotRegisters(List<Customer> notRegisters) {
        this.notRegisters = notRegisters;
    }

    public List<Customer> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Customer> registers) {
        this.registers = registers;
    }

    public List<Customer> getOtherBanks() {
        return otherBanks;
    }

    public void setOtherBanks(List<Customer> otherBanks) {
        this.otherBanks = otherBanks;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public Map<Customer, ArrayList<Transaction>> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<Customer, ArrayList<Transaction>> transactions) {
        this.transactions = transactions;
    }

    public List<Transfer> getPendingTransfers() {
        return pendingTransfers;
    }

    public void setPendingTransfers(List<Transfer> pendingTransfers) {
        this.pendingTransfers = pendingTransfers;
    }

    public List<Integer> getTrackingNumbers() {
        return trackingNumbers;
    }

    public void setTrackingNumbers(List<Integer> trackingNumbers) {
        this.trackingNumbers = trackingNumbers;
    }

    public List<SimCard> getAllSimCards() {
        return allSimCards;
    }

    public void setAllSimCards(List<SimCard> allSimCards) {
        this.allSimCards = allSimCards;
    }

    public Map<Customer, ArrayList<Fund>> getAllFunds() {
        return allFunds;
    }

    public void setAllFunds(Map<Customer, ArrayList<Fund>> allFunds) {
        this.allFunds = allFunds;
    }

    public Map<Customer, ArrayList<Loan>> getAllLoans() {
        return allLoans;
    }

    public void setAllLoans(Map<Customer, ArrayList<Loan>> allLoans) {
        this.allLoans = allLoans;
    }

    public void addToRegisters(Customer customer) {
        notRegisters.remove(customer);
        customer.setCard(createCard());
        customer.getCard().setPassword("1234");
        registers.add(customer);
    }

    public void addToNotRegisters(Customer customer) {
        this.getNotRegisters().add(customer);
    }

    private Card createCard() {
        Random random = new Random();
        String cardNumber = "";
        boolean equals = false;
        while (true) {
            equals = false;
            cardNumber = "1" + random.nextInt(10000);
            if (cardNumber.length() == 5) {
                for (int i = 0; i < this.getRegisters().size(); i++) {
                    if (cardNumber.equals(registers.get(i).getCard().getCardNumber())) {
                        equals = true;
                        break;
                    }
                }
                if (!equals) {
                    return new Card(cardNumber);
                }
            } else {
                cardNumber = "1" + random.nextInt(10000);
            }
        }
    }

    public boolean havePhoneNumber(String phoneNumber) {
        for (Customer customer : registers) {
            if (phoneNumber.equals(customer.getSimCard().getPhoneNumber())) {
                return true;
            }
        }
        for (Customer customer : notRegisters) {
            if (phoneNumber.equals(customer.getSimCard().getCharge())) {
                return true;
            }
        }
        return false;
    }

    public boolean haveNationalCode(String nationalCose) {
        for (Customer customer : registers) {
            if (nationalCose.equals(customer.getNationalCode())) {
                return true;
            }
        }
        for (Customer customer : notRegisters) {
            if (nationalCose.equals(customer.getNationalCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean inRegister(String phoneNumber, String password) {
        for (Customer customer : registers) {
            if (phoneNumber.equals(customer.getSimCard().getPhoneNumber()) && password.equals(customer.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean inNotRegister(String phoneNumber, String password) {
        for (Customer customer : notRegisters) {
            if (phoneNumber.equals(customer.getSimCard().getPhoneNumber()) && password.equals(customer.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public Customer findCustomerWithPhoneNumber(String phoneNumber) {
        for (Customer customer : registers) {
            if (phoneNumber.equals(customer.getSimCard().getPhoneNumber())) {
                return customer;
            }
        }
        return null;
    }

    public Customer findCustomerWithCardNumber(String cardNumber) {
        for (Customer customer : registers) {
            if (cardNumber.equals(customer.getCard().getCardNumber())) {
                return customer;
            }
        }
        return null;
    }

    public int createTrackingNumber() {
        Random random = new Random();
        int trackingNumber;
        boolean equals = false;
        while (true) {
            equals = false;
            trackingNumber = random.nextInt(10000);
            if (MainActivity.digits(trackingNumber) == 4) {
                for (int i = 0; i < this.getTrackingNumbers().size(); i++) {
                    if (trackingNumber == trackingNumbers.get(i)) {
                        equals = true;
                        break;
                    }
                }
                if (!equals) {
                    return trackingNumber;
                }
            } else {
                trackingNumber = random.nextInt(10000);
            }
        }
    }

    public Transaction findWithTrackingNumber(int trackingNumber) {
        List<Transaction> transactions1 = new ArrayList<>();
        for (Map.Entry<Customer, ArrayList<Transaction>> entry : this.getTransactions().entrySet()) {
            transactions1.addAll(entry.getValue());
        }
        for (Transaction transaction : transactions1) {
            if (trackingNumber == transaction.getTrackingNumber()) {
                return transaction;
            }
        }
        return null;
    }
}