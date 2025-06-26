package ir.ac.kntu;

import java.util.Date;

public class LoanThread extends Thread {
    private Bank myBank;

    public LoanThread(Bank myBank) {
        super();
        this.myBank = myBank;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            handleLoan();
            try {
                Thread.sleep(1000 * 30);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void handleLoan() {
        for (final UserAccount user : myBank.getUserAccounts()) {
            boolean isAccept = true;
            for (final Loan loan : user.getLoans()) {
                if (loan.getLoanStatus() == LoanStatus.ACCEPTED) {
                    Date date = new Date();
                    for (final Installment installment : loan.getInstallments()) {
                        if (installment.getPayDate().getTime() + 1000 * 2 * 60 < date.getTime()  && installment.getStatus() == InstallmentStatus.OVERDUE) {
                            isAccept = false;
                        }
                    }
                }
            }
            handleAcceptLoan(isAccept, user);
        }
    }

    public void handleAcceptLoan(boolean isAccept, UserAccount user) {
        if (isAccept) {
            for (final Loan loan : user.getLoans()) {
                if (loan.getLoanStatus() == LoanStatus.PENDING) {
                    user.setBalanceAccount(user.getBalanceAccount() + loan.getAmountLoan());
                    loan.setLoanStatus(LoanStatus.ACCEPTED);
                }
            }
        } else {
            for (final Loan loan : user.getLoans()) {
                if (loan.getLoanStatus() == LoanStatus.PENDING) {
                    loan.setLoanStatus(LoanStatus.FAILED);
                }
            }
        }
    }

}
