package ir.ac.kntu;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Installment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int amountInstallment;
    private int numInstallment;
    private Date payDate;
    private InstallmentStatus status;
    private boolean isPaid;

    public Installment(int amountInstallment, int numInstallment, Date payDate) {
        this.amountInstallment = amountInstallment;
        this.numInstallment = numInstallment;
        this.payDate = payDate;
        status = InstallmentStatus.OVERDUE;
    }

    public Installment() {
    }

    public int getAmountInstallment() {
        return amountInstallment;
    }

    public void setAmountInstallment(int amountInstallment) {
        this.amountInstallment = amountInstallment;
    }

    public int getNumInstallment() {
        return numInstallment;
    }

    public void setNumInstallment(int numInstallment) {
        this.numInstallment = numInstallment;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public InstallmentStatus getStatus() {
        return status;
    }

    public void setStatus(InstallmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        String date = simpleDateFormat.format(payDate);
        return  "Amount Installment=" + amountInstallment +
                ", Number Installment=" + numInstallment +
                ", Pay Date=" + date +
                ", Status =" + status;
    }
}
