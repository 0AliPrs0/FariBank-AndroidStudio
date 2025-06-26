package ir.ac.kntu;

import java.io.Serializable;

public class CapitalFund implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fundName;
    private FundType fundType;
    private int fundBalance;

    public CapitalFund() {
    }

    public CapitalFund(String fundName, FundType fundType, int fundBalance) {
        this.fundName = fundName;
        this.fundType = fundType;
        this.fundBalance = fundBalance;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public FundType getFundType() {
        return fundType;
    }

    public void setFundType(FundType fundType) {
        this.fundType = fundType;
    }

    public int getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(int fundBalance) {
        this.fundBalance = fundBalance;
    }

    @Override
    public String toString() {
        return  "fundName='" + fundName + '\'' +
                ", fundType=" + fundType +
                ", fundBalance=" + fundBalance;
    }
}
