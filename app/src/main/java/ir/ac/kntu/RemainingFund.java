package ir.ac.kntu;

import java.io.Serializable;

public class RemainingFund extends CapitalFund implements Serializable {
    private static final long serialVersionUID = 1L;
    public RemainingFund() {
        super("remaining fund", FundType.REMAINING_FUND, 0);
    }
}
