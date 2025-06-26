package ir.ac.kntu;

import java.io.Serializable;

public class Requests extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userPhone;
    private String userMassage;
    private String supportMassage;

    public Requests() {
        super();
    }

    public Requests(String userPhone, String userMassage, String supportMassage) {
        super();
        this.userMassage = userMassage;
        this.supportMassage = supportMassage;
        this.userPhone = userPhone;
    }

    public String getUserMassage() {
        return userMassage;
    }

    public void setUserMassage(String userMassage) {
        this.userMassage = userMassage;
    }

    public String getSupportMassage() {
        return supportMassage;
    }

    public void setSupportMassage(String supportMassage) {
        this.supportMassage = supportMassage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "user massage ='" + userMassage +
                "' , support massage ='" + supportMassage + '\'';
    }
}
