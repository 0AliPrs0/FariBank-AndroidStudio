package ir.ac.kntu;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;
    private int amountLoan;
    private int numInstallments;
    private boolean[] isPayInstallment;
    private Date startDatePay;
    private LoanStatus loanStatus;
    private List<Installment> installments = new ArrayList<>();

    public Loan() {
    }

    public Loan(int amountLoan, int numInstallments, Date startDatePay) {
        this.amountLoan = amountLoan;
        this.numInstallments = numInstallments;
        isPayInstallment = new boolean[numInstallments];
        this.startDatePay = startDatePay;
        loanStatus = LoanStatus.PENDING;
        for (int i = 1; i <= numInstallments; i++) {
            installments.add(new Installment(amountLoan / numInstallments, i, new Date(startDatePay.getTime() + (long) i * 2 * 60 * 1000)));
        }
    }

    public int getAmountLoan() {
        return amountLoan;
    }

    public void setAmountLoan(int amountLoan) {
        this.amountLoan = amountLoan;
    }

    public int getNumInstallments() {
        return numInstallments;
    }

    public void setNumInstallments(int numInstallments) {
        this.numInstallments = numInstallments;
    }

    public boolean[] getIsPayInstallment() {
        return isPayInstallment;
    }

    public void setIsPayInstallment(boolean[] isPayInstallment) {
        this.isPayInstallment = isPayInstallment;
    }

    public Date getStartDatePay() {
        return startDatePay;
    }

    public void setStartDatePay(Date startDatePay) {
        this.startDatePay = startDatePay;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String date = simpleDateFormat.format(startDatePay);
        return  "Amount Loan=" + amountLoan +
                ", number of installments=" + numInstallments +
                ", Start Date Pay=" + date;
    }
}
