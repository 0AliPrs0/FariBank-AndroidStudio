package ir.ac.kntu;


import java.io.Serializable;

public class BonusFund extends CapitalFund implements Serializable {
    private static final long serialVersionUID = 1L;

    public BonusFund() {
        super();
    }

    public BonusFund(int fundBalance, String fundName) {
        super(fundName, FundType.BONUS_FUND, fundBalance);
    }

}
