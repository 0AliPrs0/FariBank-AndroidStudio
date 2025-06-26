package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class TransferThread extends Thread {
    private Bank myBank;
    public TransferThread(Bank myBank){
        super();
        this.myBank = myBank;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            handleTransfer();
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void handleTransfer(){
        final List<Transfer> transfers = new ArrayList<>();
        for (final Transfer transfer : myBank.getTransfers()) {
            final int money = transfer.getAmountTransfer();
            int wadge = 1000;

            transfer.getBeginningAccount().addTransfer(new Transfer(-transfer.getAmountTransfer(), transfer.getBeginningAccount(), transfer.getDestinationAccount()));
            transfer.getDestinationAccount().addTransfer(new Transfer(transfer.getAmountTransfer(), transfer.getBeginningAccount(), transfer.getDestinationAccount()));
            transfer.getBeginningAccount().setBalanceAccount(transfer.getBeginningAccount().getBalanceAccount() - money - wadge - remainingFundHandler(money + wadge, transfer.getBeginningAccount()));
            transfer.getDestinationAccount().setBalanceAccount(transfer.getDestinationAccount().getBalanceAccount() + money);
            transfers.add(transfer);
        }
        myBank.getTransfers().removeAll(transfers);
    }

    public int remainingFundHandler(int money, UserAccount myAccount) {
        final int moneyRemain = computingRemainingMoney(money);
        if (moneyRemain + money > myAccount.getBalanceAccount()) {
            return money;
        } else {
            myAccount.getRemainingFund().setFundBalance(myAccount.getRemainingFund().getFundBalance() + moneyRemain);
            return money + moneyRemain;
        }
    }

    public int computingRemainingMoney(int money) {
        int copyMoney = money;
        int countRagam = 0;
        while (copyMoney > 0) {
            countRagam++;
            copyMoney /= 10;
        }

        final int sum = (int) (money / Math.pow(10, (countRagam * 3 / 4)) + 1) * (int) (Math.pow(10, (countRagam * 3 / 4)));
        return sum - money;
    }
}
