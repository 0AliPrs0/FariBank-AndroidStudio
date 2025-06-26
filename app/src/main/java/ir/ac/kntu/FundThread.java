package ir.ac.kntu;

public class FundThread extends Thread {
    private Bank myBank;
    public FundThread(Bank myBank){
        super();
        this.myBank = myBank;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            handleFund();
            try {
                Thread.sleep(1000 * 30);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void handleFund(){
        for (final UserAccount  user : myBank.getUserAccounts()){
            for (final BonusFund fund : user.getBonusFunds()){
                fund.setFundBalance((int) (fund.getFundBalance() * 1.02));
            }
        }
    }

}
