package ir.ac.kntu;

import java.io.Serializable;

public class ProfitFund extends CapitalFund implements Serializable {
    private static final long serialVersionUID = 1L;
    public ProfitFund() {
        super();
    }

    public ProfitFund(String fundName) {
        super(fundName, FundType.PROFIT_FUND, 0);
    }

}
